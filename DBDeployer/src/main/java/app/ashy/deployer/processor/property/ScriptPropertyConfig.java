package app.ashy.deployer.processor.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import app.ashy.deployer.processor.util.AppUtil;

@Configuration
@PropertySource({"classpath:deployer/dbdeployer.properties"})
public class ScriptPropertyConfig implements EnvironmentAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScriptPropertyConfig.class); 
	
	@Override
	public void setEnvironment(Environment env) {
		DeployerPropertyHolder.setScriptPath(env.getProperty("deployer.path"));
		DeployerPropertyHolder.setStrictScan(AppUtil.toBoolean(env.getProperty("deployer.strictscan")));
		LOGGER.info("Properties values set");
	}
}
