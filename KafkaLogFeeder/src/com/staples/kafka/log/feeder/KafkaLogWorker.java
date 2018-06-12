package com.staples.kafka.log.feeder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.staples.kafka.log.pojo.LogFile;

@Component
@Scope("prototype")
public class KafkaLogWorker implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(KafkaLogWorker.class);
	private final KafkaProducer<String, String> producer;
	private final LogFile logFile;
	

	public KafkaLogWorker(LogFile logFile, KafkaProducer<String, String> producer) {
		this.producer=producer;
		this.logFile = logFile;
	}

	public void run() {
		int messageNo = 1;
		File file=null,fileOld=null;
		RandomAccessFile raf=null,rafOld = null;
		try {
			file=new File(logFile.getLogfilePath());
			fileOld=new File(logFile.getRolledOutLogfilePath());
			if (!file.exists() || file.isDirectory() || !file.canRead()) {
				throw new IOException("Can't read this file.");
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		long filePointer = file.length();

		logger.debug("Log tailing started on " + file.getName());

		while (true) {
			try {
				long len = file.length();
				if (len < filePointer) {

					// Log must have been rolled or deleted.
					// File must have had something added to it!
					logger.debug("Log file was reset. Restarting logging from start of file.");
					logger.debug("Reading rolled out file data");
					try {
						rafOld = new RandomAccessFile(fileOld, "r");
						rafOld.seek(filePointer);
						String line = null;
						while ((line = rafOld.readLine()) != null) {
							postLogToKafka(line,messageNo);
							++messageNo;
						}

						logger.debug("Rolled out file data reading completed");
						filePointer = 0;
					}catch (FileNotFoundException e) {
						//logger.error("File Not Found", e);
						//handling the exception as The process cannot access the file because it is being used by another process [application log4j rolling out process]
					}
				}
				else if (len > filePointer) {
					// File must have had something added to it!
					raf = new RandomAccessFile(file, "r");
					raf.seek(filePointer);
					String line = null;
					while ((line = raf.readLine()) != null) {
						postLogToKafka(line,messageNo);
						++messageNo;
					}
					filePointer = raf.getFilePointer();
				}
			} catch (IOException e) {
				logger.error("IO Exception", e);
			}finally {
				try {
					if(raf!=null) {
						raf.close();
					}
					if(rafOld!=null) {
						rafOld.close();
					}
				} catch (IOException e) {
					logger.error("Not able to close the RandomAccessFile", e);
					e.printStackTrace();
				}
			}
		}
	}

	private void postLogToKafka(String messageStr, int messageNo) {
		long startTime = System.currentTimeMillis();
		if (logFile.getIsAync()) { // Send asynchronously
			producer.send(new ProducerRecord<>(logFile.getKafkaTopic(),
					String.valueOf(messageNo),
					messageStr), new DemoCallBackThread(startTime, messageNo, messageStr));
		} else { // Send synchronously
			try {
				producer.send(new ProducerRecord<>(logFile.getKafkaTopic(),
						String.valueOf(messageNo),
						messageStr)).get();
				logger.debug("Sent message: (" + messageNo + ", " + messageStr + ")");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				// handle the exception
			}
		}
	}
}

class DemoCallBackThread implements Callback {
	private static final Logger logger = LoggerFactory.getLogger(DemoCallBackThread.class);
	private final long startTime;
	private final int key;
	private final String message;

	public DemoCallBackThread(long startTime, int key, String message) {
		this.startTime = startTime;
		this.key = key;
		this.message = message;
	}

	/**
	 * onCompletion method will be called when the record sent to the Kafka Server has been acknowledged.
	 * 
	 * @param metadata  The metadata contains the partition and offset of the record. Null if an error occurred.
	 * @param exception The exception thrown during processing of this record. Null if no error occurred.
	 */
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		long elapsedTime = System.currentTimeMillis() - startTime;
		if (metadata != null) {
			logger.debug(
					"message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
					"), " +
					"offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
		} else {
			exception.printStackTrace();
		}
	}
}
