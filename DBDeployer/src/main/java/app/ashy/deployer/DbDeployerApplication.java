package app.ashy.deployer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import app.ashy.deployer.processor.MainProcessor;

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