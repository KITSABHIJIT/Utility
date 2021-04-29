package com.build.pdf.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HTMLToPDF {

	public static void convertHTMLToPDF(String content){
		try {
		    OutputStream file = new FileOutputStream(new File(CommonConstants.FILE_PATH+System.getProperty("file.separator")+"Test.pdf"));
		    Document document = new Document();
		    PdfWriter writer = PdfWriter.getInstance(document, file);
		    document.open();
		    InputStream is = new ByteArrayInputStream(content.getBytes());
		    XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
		    document.close();
		    file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//String k = "<html><body> This is my Project </body></html>";
		StringBuffer buffer=new StringBuffer();
		for(String s:FileUtil.readFromFile(CommonConstants.FILE_PATH+System.getProperty("file.separator")+"Clock.html")){
			buffer.append(s);
		}
		convertHTMLToPDF(buffer.toString());
	}

}
