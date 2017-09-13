package com.staples.request.workers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.staples.request.service.MQListnerService;


@SpringBootApplication
@ComponentScan(basePackages = "com.staples.request")
public class MessageListener {

	private final static Logger logger = LoggerFactory.getLogger(MessageListener.class.getClass());
	public static void main(String[] args) {

		ConfigurableApplicationContext context=SpringApplication.run(MessageListener.class, args);
		logger.debug("Starting Message Listener!!");
		try {
				context.getBean(MQListnerService.class).startListening();
		} catch (BeansException e) {
			logger.error(MessageListener.class.getName(),e);
			e.printStackTrace();
		}finally{
			((ConfigurableApplicationContext)context).close();
		}
	}
}
