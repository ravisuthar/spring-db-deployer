package app.ashy.deployer.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.ashy.deployer.exception.DBProcessingException;
import app.ashy.deployer.exception.DBSchemaException;

@Component
public class MainProcessor {

	@Autowired
	private ChangeLogProcessor changeLogProcessor;
	
	@Autowired
	private DeploymentProcessor deploymentProcessor;
	
	private static Logger LOGGER = LoggerFactory.getLogger(MainProcessor.class);
	
	public void process() {
		try {
			changeLogProcessor.checkChangeLogTable();
			deploymentProcessor.scanAndExecuteScripts();
		} catch (DBSchemaException e) {
			LOGGER.error("DBSchemaException: ", e);
		} catch (DBProcessingException e) {
			LOGGER.error("DBProcessingException: ", e);
		} catch(Exception e) {
			LOGGER.error("Generic Exception: ", e);
		}
	}
}
