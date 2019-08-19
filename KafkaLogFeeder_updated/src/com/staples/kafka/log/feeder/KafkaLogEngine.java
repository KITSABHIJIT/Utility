package com.staples.kafka.log.feeder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		boolean postToKafka=Boolean.parseBoolean(PropertiesUtil.getProperty("post.to.kafka"));
		logger.info("Starting Kafka Log Engine!!");
		logger.info("Start sending Logs to Kafka Instance....");
		ConfigUtil configUtil =ConfigUtil.getInstance();
		KafkaProducer<String, String> producer =(postToKafka)?configUtil.getKafkaProducer():null;
		List<LogFile> logFileList = configUtil.getLogFileList();
		ExecutorService executorService = Executors.newFixedThreadPool(logFileList.size());
		try {
			for(LogFile file:logFileList) {
				executorService.execute(new KafkaLogWorker(file,producer));
				//FileHandler fileHandler =new FileHandler(file.getLogfilePath(),1,false,false,file.getKafkaTopic(),false,producer);
				//fileHandler.start();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		if (executorService != null) {
			// shutdown
			if (!executorService.isShutdown()) {
				executorService.shutdown();
			}
		}
	}
}
