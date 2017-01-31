package main;

import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DocumentBuild {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	      try {
	         // use the factory to create a documentbuilder
	         DocumentBuilder builder = factory.newDocumentBuilder();

	         // create a new document from input source
	         FileInputStream fis = new FileInputStream("C://Workspace/GPAS_AUTH_ENGINE/MQPostMessageProject/xml/RTLRequestSample.xml");
	         InputSource is = new InputSource(fis);
	         Document doc = builder.parse(is);
	         

	         // get the first element
	         Element element = doc.getDocumentElement();
	         // get all child nodes
	         NodeList nodes = element.getChildNodes();
	         // print the text content of each child
	         for (int i = 0; i < nodes.getLength(); i++) {
	            System.out.println(nodes.item(i).getNodeName() +"::" + nodes.item(i).getTextContent());
	            if(nodes.item(i).getNodeName().equalsIgnoreCase("Content"))
	            {
	            	
	            	String cdata = nodes.item(i).getTextContent();
	            	System.out.println("cdata :: "+cdata);
	            	
	            	
	            	String strguid =  getAlphaNumeric(27);
	            	strguid = "RTLRR"+strguid;
	            	
	            	cdata = cdata.replace(cdata.substring(cdata.indexOf("<Parent-GUID>"), cdata.indexOf("</Parent-GUID>")), "<Parent-GUID>"+strguid);
	            	cdata = cdata.replace(cdata.substring(cdata.indexOf("<Child-GUID>"), cdata.indexOf("</Child-GUID>")), "<Child-GUID>"+strguid);
	            	cdata = cdata.replace(cdata.substring(cdata.indexOf("<Client-Reference-Key>"), cdata.indexOf("</Client-Reference-Key>")), "<Client-Reference-Key>"+strguid);
	            	
	            	nodes.item(i).setTextContent(cdata);
	            	break;
	            			
	            }
	            
	         }
	         
	         System.out.println("After modifying :: "+toString(doc));
	         
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }

	}

	
	
	public static String toString(Document doc) {
		try {
			StringWriter sw = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(sw));
			String output = sw.getBuffer().toString();
			output = output.replaceAll("&lt;", "<");
			output = output.replaceAll("&gt;", ">");
			return output;

		} catch (Exception ex) {
	        throw new RuntimeException("Error converting to String", ex);
	    }
	}
	
	
	 public static String getAlphaNumeric(int len) {
			final String ALPHA_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			StringBuffer sb = new StringBuffer(len);
			for (int i = 0; i < len; i++) {
				int ndx = (int) (Math.random() * ALPHA_NUM.length());
				sb.append(ALPHA_NUM.charAt(ndx));
			}
			return sb.toString();
		}
}
