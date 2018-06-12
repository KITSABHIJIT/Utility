package com.staples.kafka.log.feeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.staples.kafka.log.*")
@PropertySource(value={"file:./config/application.properties"})
public class KafkaLogEngine {
	
	private final static Logger logger = LoggerFactory.getLogger(KafkaLogEngine.class.getClass());
	public static void main(String[] args) throws Exception  {
		logger.info("Starting Kafka Log Engine!!");
		ConfigurableApplicationContext context=SpringApplication.run(KafkaLogEngine.class, args);
		try {
				context.getBean(KafkaLogFeeder.class).runProcess();
		} catch (BeansException e) {
			logger.error(KafkaLogEngine.class.getName(),e);
			e.printStackTrace();
		}finally{
			((ConfigurableApplicationContext)context).close();
		}
	}
}
