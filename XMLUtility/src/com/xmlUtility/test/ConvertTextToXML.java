package com.xmlUtility.test;

import java.io.*;

import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

public class ConvertTextToXML {

	BufferedReader in;
	StreamResult out;
	TransformerHandler th;

	public static void main(String args[]) {
		new ConvertTextToXML().createInput(FileConstants.INPUT_HEADER_SKELETON,FileConstants.INPUT_HEADER_XML);
		new ConvertTextToXML().createInput(FileConstants.INPUT_DETAIL_SKELETON,FileConstants.INPUT_DETAIL_XML);
	}

	public void createInput(String input,String output) {
		try {
			in = new BufferedReader(new FileReader(input));
			out = new StreamResult(output);
			openXml("input");
			String str;
			while ((str = in.readLine()) != null) {
				processInput(str);
			}
			in.close();
			closeXml("input");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createOutput(String input,String output) {
		try {
			in = new BufferedReader(new FileReader(input));
			out = new StreamResult(output);
			openXml("output");
			String str;
			while ((str = in.readLine()) != null) {
				processOutput(str);
			}
			in.close();
			closeXml("output");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openXml(String node) throws ParserConfigurationException, TransformerConfigurationException, SAXException {

		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		th = tf.newTransformerHandler();

		// pretty XML output
		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");

		th.setResult(out);
		th.startDocument();
		th.startElement(null, null, node, null);
	}

	public void processInput(String s) throws SAXException {
		th.startElement(null, null, "node", null);
		String[] data=s.split("[,]");
		// Adding name node
		String fieldId=data[0].trim();
		th.startElement(null, null, "field-id", null);
		th.characters(fieldId.toCharArray(), 0, fieldId.length());
		th.endElement(null, null, "field-id");

		// Adding field-name node
		String fieldName=data[1].trim();
		th.startElement(null, null, "field-name", null);
		th.characters(fieldName.toCharArray(), 0, fieldName.length());
		th.endElement(null, null, "field-name");
		
		// Adding field-type node
		String fieldType=data[2].trim();
		th.startElement(null, null, "field-type", null);
		th.characters(fieldType.toCharArray(), 0, fieldType.length());
		th.endElement(null, null, "field-type");
		
		// Adding field-length node
		String fieldlength=data[3].trim();
		th.startElement(null, null, "field-length", null);
		th.characters(fieldlength.toCharArray(), 0, fieldlength.length());
		th.endElement(null, null, "field-length");
		
		// Adding decimal-position node
		String decimalPosition=data[4].trim();
		th.startElement(null, null, "decimal-position", null);
		th.characters(decimalPosition.toCharArray(), 0, decimalPosition.length());
		th.endElement(null, null, "decimal-position");
	
		// Adding start-position node
		int startPosition=Integer.parseInt(data[5].trim());
		String startPositionStr=String.valueOf(startPosition-1);
		th.startElement(null, null, "start-position", null);
		th.characters(startPositionStr.toCharArray(), 0, startPositionStr.length());
		th.endElement(null, null, "start-position");

		// Adding end-position node
		th.startElement(null, null, "end-position", null);
		int fieldLength=Integer.parseInt(fieldlength);
		String endPosition=String.valueOf(startPosition+fieldLength-1);
		th.characters(endPosition.toCharArray(), 0, endPosition.length());
		th.endElement(null, null, "end-position");

		// Adding description node
		String desc=data[6].trim();
		th.startElement(null, null, "description", null);
		th.characters(desc.toCharArray(), 0, desc.length());
		th.endElement(null, null, "description");
		
		th.endElement(null, null, "node");
	}
	public void processOutput(String s) throws SAXException {
		String[] data=s.split("[,]");
		th.startElement(null, null, data[0], null);
		th.endElement(null, null, data[0]);
	}

	public void closeXml(String node) throws SAXException {
		th.endElement(null, null, node);
		th.endDocument();
	}
}