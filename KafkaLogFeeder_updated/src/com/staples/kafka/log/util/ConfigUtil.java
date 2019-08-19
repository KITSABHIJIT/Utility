package com.staples.kafka.log.util;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.staples.kafka.log.pojo.LogFile;

public class ConfigUtil {

	private KafkaProducer<String, String> producer;
	private HttpURLConnection conn;
	private final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
	private List<LogFile> nodeData;
	private boolean postToRelic=Boolean.parseBoolean(PropertiesUtil.getProperty("post.to.relic"));
	private boolean postToKafka=Boolean.parseBoolean(PropertiesUtil.getProperty("post.to.kafka"));

	public ConfigUtil() {
		if(postToKafka) {
			initializeKafkaLogProducer();
		}
		if(postToRelic) {
			initializeRelicHost();
		}
		initializeLogNodeList();
	}

	public static ConfigUtil getInstance() {
		return new ConfigUtil();
	}
	public KafkaProducer<String, String> initializeKafkaLogProducer() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", PropertiesUtil.getProperty("kafka.host.url") + ":" + PropertiesUtil.getProperty("kafka.port"));
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(properties);
		LOG.debug("**********************KafkaProducer Initialized**********************");
		return producer;
	}

	public void initializeRelicHost() {
		URL url=null;
		try {
			url = new URL(PropertiesUtil.getProperty("relic.host"));
			conn = (HttpURLConnection) url.openConnection();
			LOG.debug("**********************Relic Host Initialized**********************");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public List<LogFile> initializeLogNodeList(){
		nodeData=new ArrayList<LogFile>();
		try {
			File fXmlFile = new File(PropertiesUtil.getProperty("log.config.path"));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.root"));
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					LogFile logFile=new LogFile();
					logFile.setApplicationID(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.applicationId")).item(0).getTextContent());
					logFile.setJobName(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.jobName")).item(0).getTextContent());
					logFile.setLogLevel(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.logLevel")).item(0).getTextContent());
					logFile.setLogfilePath(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.fileName")).item(0).getTextContent());
					logFile.setRolledOutLogfilePath(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.rolledOutfileName")).item(0).getTextContent());
					logFile.setKafkaTopic(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.kafkaTopic")).item(0).getTextContent());
					logFile.setIsAync(Boolean.parseBoolean(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.isAsync")).item(0).getTextContent()));
					List<String> excludedLogs=new ArrayList<String>();
					if(null!=eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.excludedLogs"))) {
						Node exludedLogNode = eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.excludedLogs")).item(0);
						if(null!=exludedLogNode) {
							NodeList excludedLogList =exludedLogNode.getChildNodes();
							for (int i = 0; i < excludedLogList.getLength(); i++) {
								Node exludedLogNd = excludedLogList.item(i);
								if (exludedLogNd.getNodeType() == Node.ELEMENT_NODE) {
									if(!StringUtil.isBlankOrEmpty(exludedLogNd.getTextContent())) {
										excludedLogs.add(exludedLogNd.getTextContent());
									}
								}
							}
						}
					}
					logFile.setExcludedLog(excludedLogs);
					LOG.debug(logFile.toString());
					nodeData.add(logFile);
				}
			}
			LOG.debug("**********************LogNodeList Initialized**********************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeData;
	}


	public KafkaProducer<String, String> getKafkaProducer(){
		LOG.debug("**********************KafkaProducer Requested**********************");
		return producer;
	}

	public List<LogFile> getLogFileList(){
		LOG.debug("**********************LogNodeList Requested**********************");
		return nodeData;
	}
	
	public HttpURLConnection getRelicHost(){
		LOG.debug("**********************Relic Host Requested**********************");
		return conn;
	}
}
