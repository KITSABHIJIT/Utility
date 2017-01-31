package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import main.rkm.RKMEngineSvc27;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WriteXMLFile {
	private static final Logger LOG = LoggerFactory.getLogger(WriteXMLFile.class);
	
	public int stan = 1;
	
	public String writeXmlFile()
	{
		try
		{
			/*DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Message");
			// set attribute to staff element
			Attr attr = doc.createAttribute("Envelope");
			rootElement.setAttributeNode(attr);
			doc.appendChild(rootElement);
			
			// Message ID elements
			Element messageId = doc.createElement("MessageId");
			messageId.appendChild(doc.createTextNode(getAlphaNumeric(10)));
			rootElement.appendChild(messageId);
			
			// MessageDtTime elements
			Element messageDtTime = doc.createElement("MessageDtTime");
			messageDtTime.appendChild(doc.createTextNode(getCurrentTimeMilliesOne()));
			rootElement.appendChild(messageDtTime);
			
			// Content Element
			Element content = doc.createElement("Content");
			content.appendChild(doc.createTextNode(encryptMessage()));
			rootElement.appendChild(content);*/
			
			//return toString(doc);
			
			return encryptMessage();
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	

	/**
     * This method is used to initialize RKM service.
     */
	public void initRKM()throws Exception {

		LOG.info("***********************************************************************************RKMInitialization");
		try{
			
			RKMEngineSvc27 rkm = new RKMEngineSvc27();
			boolean isRKMServerAvailable = false; //false;
			if(!isRKMServerAvailable){
				int rkmReconnectTimes = Integer.parseInt("10");
				for(int j = 0; j <= rkmReconnectTimes; j++){
					isRKMServerAvailable = rkm.initializeRKM("C:\\app\\kmc\\config\\virtualtermkc.cfg");
					if(isRKMServerAvailable){
						LOG.info("************************************************************************************isRKMServerAvailable-->"+RKMEngineSvc27.engineRKMcontext);
						break;
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	public String encryptMessage()
	{
		
		String encryptedMessage = "";
		try
		{
		StringBuilder sb = new StringBuilder();
		String filePath = "xml/RappidConnect.xml";
			   filePath = "xml/RTLRequestSampleEMV.xml";
			   //filePath = "C://Workspace/PROXY_BANK_RSP/MQPostMsgService/xml/RTLRequestSampleEMVAmex.xml";
			   //filePath = "C://Workspace/PROXY_BANK_RSP/MQPostMsgService/xml/RTLRequestSampleEMVGiftCard.xml";
			   //filePath = "C://Workspace/PROXY_BANK_RSP/MQPostMsgService/xml/RTLRequestSampleEMVStaplesOpen.xml";
			   String outputXML = messageGenerator(filePath);//Used to generate dynamic message for Proxy performance analysis
			   
		
	/*	try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String sCurrentLine;
			while ((sCurrentLine = br.readLine())!= null)
			{
				sb.append(sCurrentLine);
			}
		}
		
		if(sb.indexOf("<STAN>") > 0)
		{
		String stanGenrated =  generateStan(String.valueOf(stan), 6);
		stan++;
		sb.replace(sb.indexOf("<STAN>")+"<STAN>".length(), sb.indexOf("</STAN>"), stanGenrated);
		}
		
		String outputXML = sb.toString();
		System.out.println(sb.toString()); */
		
		// RKM
		/*initRKM();
		
		String val = outputXML.substring(outputXML.indexOf("CDATA[")+"CDATA[".length(), outputXML.indexOf("]]"));
		StringBuffer outPutbuf = new StringBuffer(outputXML);
		
		
		encryptedMessage = RKMEngineSvc27.encryptData(outputXML);*/
		encryptedMessage = outputXML;
		
		//System.out.println(encryptedMessage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return encryptedMessage;
	}
	
	
	public String messageGenerator(String filePath)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String outputXml = null;
	      try {
	         // use the factory to create a documentbuilder
	         DocumentBuilder builder = factory.newDocumentBuilder();

	         // create a new document from input source
	         FileInputStream fis = new FileInputStream(filePath);
	         InputSource is = new InputSource(fis);
	         Document doc = builder.parse(is);
	         

	         // get the first element
	         Element element = doc.getDocumentElement();
	         // get all child nodes
	         NodeList nodes = element.getChildNodes();
	         // print the text content of each child
	         for (int i = 0; i < nodes.getLength(); i++) {
	            //System.out.println(nodes.item(i).getNodeName() +"::" + nodes.item(i).getTextContent());
	            if(nodes.item(i).getNodeName().equalsIgnoreCase("Content"))
	            {
	            	
	            	String cdata = nodes.item(i).getTextContent();
	            	//System.out.println("cdata :: "+cdata);
	            	
	            	String strguid =  getAlphaNumeric(27);
	            	strguid = "RTLRR"+strguid;
	            	
	            	cdata = cdata.replace(cdata.substring(cdata.indexOf("<Parent-GUID>"), cdata.indexOf("</Parent-GUID>")), "<Parent-GUID>"+strguid);
	            	cdata = cdata.replace(cdata.substring(cdata.indexOf("<Child-GUID>"), cdata.indexOf("</Child-GUID>")), "<Child-GUID>"+strguid);
	            	cdata = cdata.replace(cdata.substring(cdata.indexOf("<Client-Reference-Key>"), cdata.indexOf("</Client-Reference-Key>")), "<Client-Reference-Key>"+strguid);
	            	cdata = "<![CDATA["+cdata+"]]>";
	            	nodes.item(i).setTextContent(cdata);
	            	break;
	            			
	            }
	            
	         }
	         
	         outputXml = toString(doc);
	        // System.out.println("Message generated for posting into the queue:: "+outputXml);
	         
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }

	      return outputXml;
	}
	
	
	 public String toString(Document doc) {
	    try {
	        StringWriter sw = new StringWriter();
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	     /* transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");*/
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
		
		public  String generateSettlementUniqueId() throws Exception{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmssSSS");
			String formattedDate = sdf.format(date);
			String autoGenNo = getAlphaNumeric(30 - formattedDate.length());
			//System.out.println(autoGenNo);
			String authRefKey = autoGenNo + formattedDate;
			LOG.info("generateUniqueId-->"+authRefKey + ";length-->"+authRefKey.length());
			return authRefKey;
		}

		   public static String getCurrentTimeMilliesOne(){

		        SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy.HH.mm.ss");//new SimpleDateFormat("MM-dd-yyyy.HH.mm.ss");//dd/MM/yyyy
		        Date now = new Date();
		        String strDate = sdfDate.format(now);
		        LOG.info("getCurrentTimeMillies-->"+strDate);
		        return strDate;

		 }
		   
		   
		 public String generateStan(String stan, int len){
			 
			 if(stan.length() == len)
			 {
				 return stan;
			 }
			 else if(stan.length() < len)
			 {
				 return String.format("%06d", Integer.parseInt(stan));
			 }
			 else
			 {
				 return stan.substring(stan.length()-len, stan.length());
			 }
			
		 }
}
