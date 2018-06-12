package com.staples.kafka.log.feeder;

import java.util.List;

import javax.inject.Inject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.staples.kafka.log.pojo.LogFile;
import com.staples.kafka.log.util.ConfigUtil;

@Component
public class KafkaLogFeeder {
	@Inject
	public ConfigUtil configUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaLogFeeder.class);
	
	public void runProcess() {
		logger.info("Start sending Logs to Kafka Instance....");
		boolean finished = false;
		int count=0;
		ThreadPoolTaskExecutor taskExecutor =configUtil.getTaskExecutor();
		KafkaProducer<String, String> producer =configUtil.getKafkaProducer();
		List<LogFile> logFileList = configUtil.getLogFileList();
		try {
			if(logFileList.isEmpty()){
				logger.info("No Log File available to read");
			}
			while (!finished){
				if(count<logFileList.size()){
					LogFile logFile=logFileList.get(count);

					logger.debug("Acquiring a available permit - current size {}: ",taskExecutor.getActiveCount());
					logger.debug("Permit just acquired - available permits presently: {}",taskExecutor.getActiveCount());
					logger.debug("Available Threads: "+ taskExecutor.getActiveCount());
					if (taskExecutor.getActiveCount() == 0){
						logger.debug("Thread Processing request messages count reached Zero");
					}
					Runnable work= new KafkaLogWorker(logFile,producer);
					taskExecutor.execute(work);
					count=count+1;
				}
				if(taskExecutor.getActiveCount()==0 && count >= logFileList.size()){
					finished=true;
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			finished=true;
		} 
	}
}