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


package app.ashy.deployer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import app.ashy.deployer.processor.MainProcessor;

/**
 * @author Ashish
 *
 */
@SpringBootApplication
@Component
@ComponentScan(basePackages = {"app.ashy.deployer"} )
public class DbDeployerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DbDeployerApplication.class, args);
		MainProcessor mainProcessor = ctx.getBean(MainProcessor.class);
		mainProcessor.process();
	}	
}