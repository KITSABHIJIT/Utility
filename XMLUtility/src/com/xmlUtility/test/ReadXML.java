package com.xmlUtility.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {

	public static List<NodeEntity> readXMLFileWithNode(String fileName,String nodeName){
		List<NodeEntity> nodeData=new ArrayList<NodeEntity>();
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName(nodeName);

			//System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					NodeEntity nodeEntity=new NodeEntity();
					nodeEntity.setNodeName(eElement.getElementsByTagName(FileConstants.XML_FIELD).item(0).getTextContent());
					nodeEntity.setStartPosition(StringUtil.getStrToInt(eElement.getElementsByTagName(FileConstants.XML_START).item(0).getTextContent()));
					nodeEntity.setEndPosition(StringUtil.getStrToInt(eElement.getElementsByTagName(FileConstants.XML_END).item(0).getTextContent()));
					//System.out.println(nodeEntity.toString());
					nodeData.add(nodeEntity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeData;
	}
	
	
  public static void readXMLFile(String fileName) {

    try {

	File file = new File(fileName);

	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                             .newDocumentBuilder();

	Document doc = dBuilder.parse(file);

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	if (doc.hasChildNodes()) {

		printNote(doc.getChildNodes());

	}

    } catch (Exception e) {
	System.out.println(e.getMessage());
    }

  }

  private static void printNote(NodeList nodeList) {

    for (int count = 0; count < nodeList.getLength(); count++) {

	Node tempNode = nodeList.item(count);

	// make sure it's element node.
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

		// get node name and value
		System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		System.out.println("Node Value =" + tempNode.getTextContent());

		if (tempNode.hasAttributes()) {

			// get attributes names and values
			NamedNodeMap nodeMap = tempNode.getAttributes();

			for (int i = 0; i < nodeMap.getLength(); i++) {

				Node node = nodeMap.item(i);
				System.out.println("attr name : " + node.getNodeName());
				System.out.println("attr value : " + node.getNodeValue());

			}

		}

		if (tempNode.hasChildNodes()) {

			// loop again if has child nodes
			printNote(tempNode.getChildNodes());

		}

		System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

	}

    }

  }
 public static void main(String[] args) {
	  
	 // readXMLFile("resources/input.xml");
	  readXMLFileWithNode("resources/input.xml","node");
	  
  }
}