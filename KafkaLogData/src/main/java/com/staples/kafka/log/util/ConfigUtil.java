package com.staples.kafka.log.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.staples.kafka.log.pojo.LogFile;

public class ConfigUtil {

	private final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
	private List<LogFile> nodeData;

	public ConfigUtil() {
		initializeLogNodeList();
	}

	public static ConfigUtil getInstance() {
		return new ConfigUtil();
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


					logFile.setApplicationID(!StringUtil.isBlankOrEmpty(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.applicationId")).item(0))?eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.applicationId")).item(0).getTextContent():"");
					logFile.setJobName(!StringUtil.isBlankOrEmpty(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.jobName")).item(0))?eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.jobName")).item(0).getTextContent():"");
					logFile.setLogLevel(!StringUtil.isBlankOrEmpty(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.logLevel")).item(0))?eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.logLevel")).item(0).getTextContent():"");
					logFile.setLogfilePath(!StringUtil.isBlankOrEmpty(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.fileName")).item(0))?eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.fileName")).item(0).getTextContent():"");
					logFile.setRolledOutLogfilePath(!StringUtil.isBlankOrEmpty(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.rolledOutfileName")).item(0))?eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.rolledOutfileName")).item(0).getTextContent():"");
					logFile.setKafkaTopic(!StringUtil.isBlankOrEmpty(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.kafkaTopic")).item(0))?eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.kafkaTopic")).item(0).getTextContent():"");
					logFile.setIsAync(Boolean.parseBoolean(!StringUtil.isBlankOrEmpty(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.isAsync")).item(0))?(eElement.getElementsByTagName(PropertiesUtil.getProperty("log.config.node.isAsync")).item(0).getTextContent()):""));



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

	public void createXML(final List<LogFile> data) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(PropertiesUtil.getProperty("log.config.node.parent"));
			doc.appendChild(rootElement);

			for(LogFile dataObj: data) {
				// logfile elements
				Element logFile = doc.createElement(PropertiesUtil.getProperty("log.config.node.root"));
				rootElement.appendChild(logFile);

				// set attribute to applicationId element
				/*Attr attr = doc.createAttribute(PropertiesUtil.getProperty("log.config.node.applicationId"));
				attr.setValue(dataObj.getApplicationID());
				logFile.setAttributeNode(attr);*/

				// shorten way
				// staff.setAttribute("id", "1");

				// applicationId elements
				Element applicationId = doc.createElement(PropertiesUtil.getProperty("log.config.node.applicationId"));
				applicationId.appendChild(doc.createTextNode(dataObj.getApplicationID()));
				logFile.appendChild(applicationId);
				
				// jobName elements
				Element jobName = doc.createElement(PropertiesUtil.getProperty("log.config.node.jobName"));
				jobName.appendChild(doc.createTextNode(dataObj.getJobName()));
				logFile.appendChild(jobName);

				// logLevel elements
				Element logLevel = doc.createElement(PropertiesUtil.getProperty("log.config.node.logLevel"));
				logLevel.appendChild(doc.createTextNode(dataObj.getLogLevel()));
				logFile.appendChild(logLevel);

				// fileName elements
				Element fileName = doc.createElement(PropertiesUtil.getProperty("log.config.node.fileName"));
				fileName.appendChild(doc.createTextNode(dataObj.getLogfilePath()));
				logFile.appendChild(fileName);

				// rolledOutfileName elements
				Element rolledOutfileName = doc.createElement(PropertiesUtil.getProperty("log.config.node.rolledOutfileName"));
				rolledOutfileName.appendChild(doc.createTextNode(dataObj.getRolledOutLogfilePath()));
				logFile.appendChild(rolledOutfileName);

				Element kafkaTopic = doc.createElement(PropertiesUtil.getProperty("log.config.node.kafkaTopic"));
				kafkaTopic.appendChild(doc.createTextNode(dataObj.getKafkaTopic()));
				logFile.appendChild(kafkaTopic);

				Element isAsync = doc.createElement(PropertiesUtil.getProperty("log.config.node.isAsync"));
				isAsync.appendChild(doc.createTextNode(String.valueOf(dataObj.getIsAync())));
				logFile.appendChild(isAsync);
				
				if(!StringUtil.isBlankOrEmpty(dataObj.getExcludedLog())) {
					Element excludeLogList = doc.createElement(PropertiesUtil.getProperty("log.config.node.excludedLogs"));
					logFile.appendChild(excludeLogList);
					for(String excludeLogTemp:dataObj.getExcludedLog()) {
						// rolledOutfileName elements
						Element excludeLog = doc.createElement(PropertiesUtil.getProperty("log.config.node.excludedLog"));
						excludeLog.appendChild(doc.createTextNode(excludeLogTemp));
						excludeLogList.appendChild(excludeLog);
					}
				}
				
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(PropertiesUtil.getProperty("log.config.path.new")));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public List<LogFile> getLogFileList(){
		LOG.debug("**********************LogNodeList Requested**********************");
		return nodeData;
	}
}
