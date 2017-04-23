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
