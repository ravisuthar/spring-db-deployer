package app.ashy.deployer.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.ashy.deployer.exception.DBProcessingException;
import app.ashy.deployer.processor.enums.FileMeta;
import app.ashy.deployer.processor.enums.SQLType;
import app.ashy.deployer.processor.property.DeployerPropertyHolder;
import app.ashy.deployer.processor.util.AppUtil;
import app.ashy.deployer.processor.util.FileUtil;

@Component
public class DeploymentProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeploymentProcessor.class);
	private static final String FILE_PATTERN = "\\d+_(DDL|DML|PFT)_[A-z]+_[A-z]+.sql";

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ChangeLogProcessor changeLogProcessor;

	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = RuntimeException.class)
	public void scanAndExecuteScripts() throws SQLException {
		String scriptPath = FileUtil.getScriptFolderPath();
		if (AppUtil.isEmpty(scriptPath)) {
			throw new DBProcessingException("Script location not found.");
		}
		LOGGER.info("Scanning scripts from: {}", scriptPath);
		File scriptDir = new File(scriptPath);
		if (scriptDir.isDirectory()) {
			for (File path : scriptDir.listFiles()) {
				if (path.isFile()) {
					LOGGER.info("Non-SQL file `{}` found, skipping...", path.getName());
					continue;
				}
				LOGGER.debug("Directory " + path.getAbsolutePath() + " found.");
				LOGGER.info("Scanning directory: {}", path.getName());
				for (File scriptFile : path.listFiles()) {
					if (validateFileName(scriptFile.getName())) {
						try {
							String fileName = FileUtil.getFileNameWithoutExtensiton(scriptFile);
							Map<FileMeta, String> fileMetaMap = FileUtil.getFileMetadata(fileName);
							if (!changeLogProcessor.isFileProcessed(fileMetaMap)) {
								String fileContent = new String(Files.readAllBytes(scriptFile.toPath()));
								Connection connection = dataSource.getConnection();
								LOGGER.debug(fileContent);
								if (fileMetaMap.get(FileMeta.FILE_TYPE).equalsIgnoreCase(SQLType.PFT.toString())) {
									Statement stmt = connection.createStatement();
									stmt.execute(fileContent.replaceAll("[\\t\\n\\r]"," "));
								} else {
									EncodedResource encodedResource = AppUtil.getEncodedResource(fileContent);
									ScriptUtils.executeSqlScript(connection, encodedResource);
								}
								LOGGER.info("Script {} executed successfully...", scriptFile.getName());
								changeLogProcessor.saveChangeLog(fileMetaMap);
							}
						} catch (IOException e) {
							if (DeployerPropertyHolder.isStrictScan()) {
								throw new DBProcessingException(
										scriptFile.getName() + " file not found. Aborting scan!", e);
							} else {
								LOGGER.warn("{} file not found. Continuing scan!", scriptFile.getName());
							}
						} catch (ScriptException e) {
							throw new DBProcessingException(scriptFile.getName() + " failed to execute, aborting!", e);
						}
					}
				}
			}
		}
	}

	private boolean validateFileName(String name) {

		Pattern filePattern = Pattern.compile(FILE_PATTERN, Pattern.CASE_INSENSITIVE);
		if (!filePattern.matcher(name).matches()) {
			if (DeployerPropertyHolder.isStrictScan()) {
				throw new DBProcessingException(
						"The file `" + name + "` does not qualify for further processing." + " Aborting the scan.");
			} else {
				LOGGER.warn("Invalid file found, continuing the scan as strict scan is disabled.");
				return false;
			}
		}
		return true;
	}
}