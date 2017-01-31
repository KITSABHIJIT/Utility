package com.test.transformer;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class Main {

	public static String getTargetXML(Map<String,List<String>> sourceDoc) throws Exception {

		DynamicXMLTransformer xmlTransformer = new DynamicXMLTransformer();

		Map<String, String> mappings = FileManager.readPropertiesFileAsMap("mappings.properties");

		Map<String, String> completeMappings = xmlTransformer.generateCompleteXpaths(mappings, sourceDoc);

		Document targetDoc = xmlTransformer.transform(completeMappings);

		DOMSource source = new DOMSource(targetDoc);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;

		StringWriter sw = new StringWriter();
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, new StreamResult(sw));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(sw.toString());
		return sw.toString();

	}
}