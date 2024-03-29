package com.test.transformer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DynamicXMLTransformer {

  private Document createTargetDocument(Map<String, String> mappings) throws Exception {

    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

    for (Entry<String, String> entry : mappings.entrySet()) {

      String targetXpath = entry.getKey();

      String[] tokens = targetXpath.split("/");
      List<String> tokensList = new ArrayList<String>();
      for (String token : tokens) {
        if (token != null & !token.trim().equals(""))
          tokensList.add(token.trim());
      }

      String previousXpath = "";
      Element parentElement = null;

      for (String token : tokensList) {

        String currentXpath = previousXpath + "/" + token;

        if (token.contains("[")) {

          String neededNode = token.split("\\[")[0];
          String indexStr = token.split("\\[")[1].split("\\]")[0];

          Integer index = Integer.valueOf(indexStr.trim());
          NodeList nodes = getNodeList(previousXpath + "/" + neededNode, document);

          int resultCount = nodes.getLength();

          if (resultCount < index) {

            Element currentParent = null;
            for (int i = resultCount + 1; i <= index; i++) {
              Element el = document.createElement(neededNode);
              parentElement.appendChild(el);

              if (i == index) {
                currentParent = el;
              }
            }

            parentElement = currentParent;
          } else {

            NodeList nodeList = getNodeList(currentXpath, document);
            parentElement = (Element) nodeList.item(0);
          }

        } else {

          NodeList nodes = getNodeList(currentXpath, document);
          int resultCount = nodes.getLength();

          if (resultCount > 0) {
            if (nodes.item(0) instanceof Element) {
              parentElement = (Element) nodes.item(0);
            }
          } else {
            if (token.contains("@")) {
              Attr attr = document.createAttribute(token.replace("@", "").trim());
              parentElement.setAttributeNode(attr);
            } else {

              if (parentElement == null) {
                Element root = document.createElement(token);
                document.appendChild(root);
                parentElement = root;
              } else {
                Element el = document.createElement(token);
                parentElement.appendChild(el);
                parentElement = el;
              }
            }
          }

        }
        previousXpath = currentXpath;
      }
    }
    return document;
  }

  public Document transform(Map<String, String> finalMappings) throws Exception {

    Document targetDoc = createTargetDocument(finalMappings);

    for (Entry<String, String> entry : finalMappings.entrySet()) {

      String path = entry.getKey();
      String value = entry.getValue();

      NodeList nodes = getNodeList(path, targetDoc);

      if (nodes.item(0) instanceof Attr) {

        ((Attr) nodes.item(0)).setNodeValue(value);

      } else if (nodes.item(0) instanceof Element) {

        ((Element) nodes.item(0)).setTextContent(value);

      }
    }
    return targetDoc;
  }

  public Map<String, String> generateCompleteXpaths(Map<String, String> mappings, Map<String,List<String>> doc) throws Exception {

    Map<String, String> finalMappings = new LinkedHashMap<String, String>();

    for (Entry<String, String> entry : mappings.entrySet()) {

      String sourceXpath = entry.getKey();
      String targetXpath = entry.getValue();

      if (sourceXpath.contains("[")) {
        Map<String, String> subMap = getArrayXPaths(targetXpath, sourceXpath, doc);
        finalMappings.putAll(subMap);

      } else {
        String sourceValue = getElementValue(sourceXpath, doc);
        if (sourceValue != null) {
          finalMappings.put(targetXpath, sourceValue);
        }
      }

    }
    return finalMappings;
  }

  private Map<String, String> getArrayXPaths(String targetXpath, String sourceXpath, Map<String,List<String>> doc) throws Exception {

    Map<String, String> subMap = new LinkedHashMap<String, String>();

    if (sourceXpath.contains("[a]")) {

      String xpath = sourceXpath.split("\\[a\\]")[0];
      List<String> nodeList=doc.get(xpath);
      String finalTargetXpath = null;
      if (nodeList != null) {

        for (int i = 1; i <= nodeList.size(); i++) {
          finalTargetXpath = targetXpath.replace("[a]", "[" + i + "]");
            String sourceValue = nodeList.get(i-1);
            if (sourceValue != null) {
              subMap.put(finalTargetXpath, sourceValue);
            }
        }
      }else{
    	  finalTargetXpath = targetXpath.replace("[a]", "[" + 1 + "]");
    	  subMap.put(finalTargetXpath, xpath);
      }
    }

    return subMap;
  }

  private NodeList getNodeList(String currentXpath, Document document) throws Exception {

    XPath xpath = XPathFactory.newInstance().newXPath();
    XPathExpression expression = xpath.compile(currentXpath);
    Object result = expression.evaluate(document, XPathConstants.NODESET);

    NodeList nodeList = (NodeList) result;

    return nodeList;

  }

  private String getElementValue(String xPath, Map<String,List<String>> document) throws Exception {
	List<String> nodeList=document.get(xPath);
    if(null !=nodeList && nodeList.size()>0){
    	return nodeList.get(0);
    }else{
    	return xPath;
    }
  }

}