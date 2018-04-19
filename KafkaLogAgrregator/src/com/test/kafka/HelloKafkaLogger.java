package com.test.kafka;

import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloKafkaLogger {
    private static final Logger logger = LoggerFactory.getLogger(HelloKafkaLogger.class);

    public static void main(String[] argv) throws InterruptedException {
    	logger.info("Inside HelloKafkaLogger.main");
    	int j=0;
           for(int i=0;i<1000000;i++) {
        	logger.debug("Debug message from HelloKafkaLogger.main: "+j );
            logger.info("Info message from HelloKafkaLogger.main: "+(++j) );
            logger.warn("Warn message from HelloKafkaLogger.main: "+(++j) );
            logger.error("Error message from HelloKafkaLogger.main: "+(++j) );
            Thread.sleep(900);
           }
           logger.info("Exiting HelloKafkaLogger.main");
        LogManager.shutdown();
    }
}
