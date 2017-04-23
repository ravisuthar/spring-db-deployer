/*
 * Copyright 2017 Ashish Gundewad

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package app.ashy.deployer.processor.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import app.ashy.deployer.processor.util.AppUtil;

/**
 * @author Ashish
 *
 */
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
