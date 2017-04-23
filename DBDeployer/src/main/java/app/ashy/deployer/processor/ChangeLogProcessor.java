package app.ashy.deployer.processor;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.ashy.deployer.entity.ChangeLogBean;
import app.ashy.deployer.exception.DBProcessingException;
import app.ashy.deployer.exception.DBSchemaException;
import app.ashy.deployer.processor.enums.FileMeta;
import app.ashy.deployer.processor.repository.ChangeLogRepository;

@Component
public class ChangeLogProcessor {

	@Autowired
	private ChangeLogRepository changeLogRepository;

	private static Logger LOGGER = LoggerFactory.getLogger(ChangeLogProcessor.class);

	@Transactional(propagation = Propagation.REQUIRED)
	public void checkChangeLogTable() {
		if (!checkChangeLogStatus()) {
			throw new DBSchemaException("ChangeLog not found!");
		}
		LOGGER.info("ChangeLog is available");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean isFileProcessed(Map<FileMeta, String> fileMetaMap) {
		ChangeLogBean logBean = changeLogRepository.findByIdentifier(Long.parseLong(fileMetaMap.get(FileMeta.FILE_NUM)));
		if (logBean != null) {
			LOGGER.info("File with Id {} has been processed. Checking for duplicate Id.", fileMetaMap.get(FileMeta.FILE_NUM));
			ChangeLogBean bean = new ChangeLogBean();
			bean.setIdentifier(Long.parseLong(fileMetaMap.get(FileMeta.FILE_NUM)));
			bean.setType(fileMetaMap.get(FileMeta.FILE_TYPE));
			bean.setOperation(fileMetaMap.get(FileMeta.FILE_OPER));
			bean.setName(fileMetaMap.get(FileMeta.FILE_NAME));
			
			if(logBean.equals(bean)) {
				LOGGER.warn("File with Id {} is not duplicate. Skipping...", fileMetaMap.get(FileMeta.FILE_NUM));
				return true;
			} else {
				throw new DBProcessingException("File found with duplicate Id: " + fileMetaMap.get(FileMeta.FILE_NUM));
			}
		}
		LOGGER.info("File with Id {} has not been processed.", fileMetaMap.get(FileMeta.FILE_NUM));
		return false;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveChangeLog(Map<FileMeta, String> fileMetaMap) {
		ChangeLogBean bean = new ChangeLogBean();
		bean.setIdentifier(Long.parseLong(fileMetaMap.get(FileMeta.FILE_NUM)));
		bean.setType(fileMetaMap.get(FileMeta.FILE_TYPE));
		bean.setOperation(fileMetaMap.get(FileMeta.FILE_OPER));
		bean.setName(fileMetaMap.get(FileMeta.FILE_NAME));
		bean.setAppliedBy(System.getProperty("user.name"));
		bean.setAppliedDate(new Date());
		
		try {
			changeLogRepository.save(bean);
		} catch (DataAccessException e) {
			throw new DBProcessingException("Failed to updating Changelog.", e);
		}
	}

	private boolean checkChangeLogStatus() {
		LOGGER.info("Checking ChangeLog status");
		try {
			long count = changeLogRepository.count();
			LOGGER.info("ChangeLog count: {}", count);
			if (count > 0) {
				ChangeLogBean changeLog = changeLogRepository.findTopByOrderByIdentifierDesc();
				LOGGER.info("ChangeLog last updated: {}", changeLog.getName());
				LOGGER.info("ChangeLog last updated on: {}", changeLog.getAppliedDate());
				LOGGER.info("ChangeLog last updated by: {}", changeLog.getAppliedBy());
			}
		} catch (DataAccessException e) {
			LOGGER.info("ChangeLog not exists");
			return false;
		}
		return true;
	}
}
