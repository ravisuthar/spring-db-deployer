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

package app.ashy.deployer.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ashish
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = {"app.ashy.deployer.processor.repository"})
@EnableTransactionManagement
public class DBConfig {

	@Bean(destroyMethod="close")
	DataSource dataSource(Environment env) {
		return DataSourceBuilder
				.create()
				.username(env.getProperty("db.username"))
				.password(env.getProperty("db.password"))
				.url(env.getProperty("db.url"))
				.driverClassName(env.getProperty("db.driver"))
				.build();
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
}
