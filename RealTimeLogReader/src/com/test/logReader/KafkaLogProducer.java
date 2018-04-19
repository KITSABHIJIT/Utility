package com.test.logReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaLogProducer{
	private final KafkaProducer<String, String> producer;
	private final String topic;
	private final Boolean isAsync;
	private final String filePath;

	public static final String KAFKA_SERVER_URL = "lwacfndbv104.staples.com";
	public static final int KAFKA_SERVER_PORT = 9092;
	public static final String CLIENT_ID = "KafkaLogProducer";

	public KafkaLogProducer(String topic,String filePath, Boolean isAsync) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
		properties.put("client.id", CLIENT_ID);
		properties.put("acks", "all");
		properties.put("retries", 0);
		properties.put("batch.size", 16384);
		properties.put("linger.ms", 1);
		properties.put("buffer.memory", 33554432);
		properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		
		producer = new KafkaProducer<>(properties);
		this.topic = topic;
		this.isAsync = isAsync;
		this.filePath = filePath;
	}

	public void run() {
		int messageNo = 1;
		File file=null,fileOld=null;
		RandomAccessFile raf=null,rafOld = null;
		try {
			file=new File(filePath);
			fileOld=new File(filePath+".1");
			if (!file.exists() || file.isDirectory() || !file.canRead()) {
				throw new IOException("Can't read this file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		long filePointer = file.length();

		System.out.println("Log tailing started on " + file.getName());

		while (true) {
			try {
				long len = file.length();
				if (len < filePointer) {

					// Log must have been rolled or deleted.
					// File must have had something added to it!
					System.out.println("Log file was reset. Restarting logging from start of file.");
					System.out.println("Reading rolled out file data");
					try {
						rafOld = new RandomAccessFile(fileOld, "r");
						rafOld.seek(filePointer);
						String line = null;
						while ((line = rafOld.readLine()) != null) {
							postLogToKafka(line,messageNo);
							++messageNo;
						}

						System.out.println("Rolled out file data reading completed");
						filePointer = 0;
					}catch (FileNotFoundException e) {
						//e.printStackTrace();
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
				e.printStackTrace();
			}finally {
				try {
					if(raf!=null) {
						raf.close();
					}
					if(rafOld!=null) {
						rafOld.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private void postLogToKafka(String messageStr, int messageNo) {
		long startTime = System.currentTimeMillis();
		if (isAsync) { // Send asynchronously
			producer.send(new ProducerRecord<>(topic,
					String.valueOf(messageNo),
					messageStr), new DemoCallBack(startTime, messageNo, messageStr));
		} else { // Send synchronously
			try {
				producer.send(new ProducerRecord<>(topic,
						String.valueOf(messageNo),
						messageStr)).get();
				System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				// handle the exception
			}
		}
	}
}

class DemoCallBack implements Callback {

	private final long startTime;
	private final int key;
	private final String message;

	public DemoCallBack(long startTime, int key, String message) {
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
			System.out.println(
					"message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
					"), " +
					"offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
		} else {
			exception.printStackTrace();
		}
	}
}
