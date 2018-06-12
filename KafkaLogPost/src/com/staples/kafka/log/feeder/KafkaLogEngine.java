package com.staples.kafka.log.feeder;

import java.util.List;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.staples.kafka.log.pojo.LogFile;
import com.staples.kafka.log.util.ConfigUtil;
import com.staples.kafka.log.util.PropertiesUtil;

public class KafkaLogEngine {
	private final static Logger logger = LoggerFactory.getLogger(KafkaLogEngine.class);
	public static void main(String[] args) throws Exception  {
		PropertiesUtil.loadProperties(args[0]);
		logger.info("Starting Kafka Log Engine!!");
		logger.info("Start sending Logs to Kafka Instance....");
		ConfigUtil configUtil =ConfigUtil.getInstance();
		KafkaProducer<String, String> producer =configUtil.getKafkaProducer();
		List<LogFile> logFileList = configUtil.getLogFileList();
		try {
			KafkaLogWorker.process(logFileList.get(0),producer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
