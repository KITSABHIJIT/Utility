package com.staples.kafka.log.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.staples.kafka.log.pojo.LogFile;

@Configuration
@ComponentScan(basePackages = "com.staples.kafka.log.*")
@PropertySource(value={"file:./config/application.properties"})
public class ConfigUtil {
	
	@Value("${thread.core.pool.size}")
	private String corePoolSize; 
	@Value("${thread.max.pool.size}")
	private String maxPoolSize;
	
	@Value("${kafka.host.url}")
	private String kafkaHost;
	@Value("${kafka.port}")
	private String kafkaPort;
	
	
	@Value("${log.config.path}")
	private String logConfig; 
	@Value("${log.config.node.root}")
	private String logConfigRootNode; 
	@Value("${log.config.node.applicationId}")
	private String applicationIdNode; 
	@Value("${log.config.node.fileName}")
	private String fileNameNode; 
	@Value("${log.config.node.rolledOutfileName}")
	private String rolledOutFileNode; 
	@Value("${log.config.node.kafkaTopic}")
	private String kafkaTopicNode; 
	@Value("${log.config.node.isAsync}")
	private String isAsync;
	private List<LogFile> nodeData;
	
	private ThreadPoolTaskExecutor executor;
	private KafkaProducer<String, String> producer;
	private final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
	
	@Bean
	   public static PropertySourcesPlaceholderConfigurer
	     propertySourcesPlaceholderConfigurer() {
	      return new PropertySourcesPlaceholderConfigurer();
	   }
	
	@Bean
	public ThreadPoolTaskExecutor initializeTaskExecutorPool() {
		executor = new ThreadPoolTaskExecutor();
		LOG.debug("**********************corePoolSize: "+corePoolSize+" **********************");
		LOG.debug("**********************maxPoolSize: "+maxPoolSize+" **********************");
		executor.setCorePoolSize(Integer.parseInt(corePoolSize));
		executor.setMaxPoolSize(Integer.parseInt(maxPoolSize));
		executor.setThreadNamePrefix("KafkaLogFeeder");
		executor.setWaitForTasksToCompleteOnShutdown(true);
		LOG.debug("**********************ThreadPoolTaskExecutor Initialized**********************");
		return executor;
	}
	
	@Bean
	public KafkaProducer<String, String> initializeKafkaLogProducer() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", kafkaHost + ":" + kafkaPort);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(properties);
		LOG.debug("**********************KafkaProducer Initialized**********************");
		return producer;
	}
	
	@Bean
	public List<LogFile> initializeLogNodeList(){
		nodeData=new ArrayList<LogFile>();
		try {
			File fXmlFile = new File(logConfig);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(logConfigRootNode);
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					LogFile logFile=new LogFile();
					logFile.setApplicationID(eElement.getElementsByTagName(applicationIdNode).item(0).getTextContent());
					logFile.setLogfilePath(eElement.getElementsByTagName(fileNameNode).item(0).getTextContent());
					logFile.setRolledOutLogfilePath(eElement.getElementsByTagName(rolledOutFileNode).item(0).getTextContent());
					logFile.setKafkaTopic(eElement.getElementsByTagName(kafkaTopicNode).item(0).getTextContent());
					logFile.setKafkaTopic(eElement.getElementsByTagName(kafkaTopicNode).item(0).getTextContent());
					logFile.setIsAync(Boolean.parseBoolean(eElement.getElementsByTagName(isAsync).item(0).getTextContent()));
					System.out.println(logFile.toString());
					nodeData.add(logFile);
				}
			}
			LOG.debug("**********************LogNodeList Initialized**********************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeData;
	}
	
	public ThreadPoolTaskExecutor getTaskExecutor(){
		LOG.debug("**********************ThreadPoolTaskExecutor Requested**********************");
		return executor;
	}
	
	public KafkaProducer<String, String> getKafkaProducer(){
		LOG.debug("**********************KafkaProducer Requested**********************");
		return producer;
	}
	
	public List<LogFile> getLogFileList(){
		LOG.debug("**********************LogNodeList Requested**********************");
		return nodeData;
	}
}
