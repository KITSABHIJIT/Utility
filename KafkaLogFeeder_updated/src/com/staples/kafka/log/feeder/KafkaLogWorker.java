package com.staples.kafka.log.feeder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.staples.kafka.log.pojo.LogFile;
import com.staples.kafka.log.util.PropertiesUtil;

public class KafkaLogWorker implements Runnable {
	private final static Logger logger =LoggerFactory.getLogger(KafkaLogWorker.class);
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
				throw new IOException("Can't read this file."+file.getAbsolutePath()+"::"+file.getName());
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
							if(validForPost(line)) {
								//postLogToKafka(line,messageNo);
								postToRelic(line);
								++messageNo;
							}
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
						if(validForPost(line)) {
							//postLogToKafka(line,messageNo);
							postToRelic(line);
							++messageNo;
						}
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

			try {
				Thread.sleep(Long.parseLong(PropertiesUtil.getProperty("log.reader.delay")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void postLogToKafka(String messageStr, int messageNo) {
		long startTime = System.currentTimeMillis();
		messageStr=logFile.getApplicationID()+" :: "+messageStr;
		if (logFile.getIsAync()) { // Send asynchronously
				producer.send(new ProducerRecord<>(logFile.getKafkaTopic(),
						String.valueOf(messageNo),
						messageStr), new DemoCallBackThread(startTime, messageNo, messageStr));
		} else { 
			// Send synchronously
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

	private boolean validForPost(String message) {
		boolean result=true;
		for(String excludes:logFile.getExcludedLog()) {
			if(message.contains(excludes)) {
				logger.debug("Log excluded: "+message);
				result=false;
				break;
			}
		}
		return result;
	}


	private void postToRelic(String messageStr) {
		try {
			URL url = new URL(PropertiesUtil.getProperty("relic.host"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", PropertiesUtil.getProperty("relic.content.type"));
			conn.addRequestProperty("X-Insert-Key", PropertiesUtil.getProperty("relic.key"));
			String input = "{"
					+ "\"eventType\":\""+PropertiesUtil.getProperty("relic.event.type")+"\","
					+ "\"application\":\""+logFile.getApplicationID()+"\","					
					+ "\"jobName\":\""+logFile.getJobName()+"\","
					+ "\"logLevel\":\""+logFile.getLogLevel()+"\","
					+ "\"LogFile\":\""+logFile.getLogfilePath()+"\","
					+ "\"LogMessage\":\""+messageStr
					+"\"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED 
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK
					&& conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			/*BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}*/
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
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
