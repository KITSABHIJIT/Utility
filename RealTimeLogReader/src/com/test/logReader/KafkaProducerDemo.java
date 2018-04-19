package com.test.logReader;

public class KafkaProducerDemo {
    public static final String TOPIC = "log_insert";
    public static final String LOG_FILE = "C:/DynamicLog/logging.log";
    public static void main(String[] args) {
        boolean isAsync = false;
       
       /* KafkaLogProducer producer = new KafkaLogProducer(TOPIC,LOG_FILE, isAsync);
        // start the producer
        producer.run();*/
        
        
        KafkaLogProducerThread producerThread = new KafkaLogProducerThread(TOPIC,LOG_FILE, isAsync);
        // start the producer
        producerThread.start();
 
    }
}