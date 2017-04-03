package com.build.pdf.util;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

public class PRFToText {

	public static void main(String[] args) {
		String dir="C:\\Users\\royab001\\Google Drive\\Domain Knowledge\\Retail Management"+System.getProperty("file.separator");
		readPDF(dir+"Ch1_Introduction to Retail.pdf");

	}
	public static void readPDF(String fileName){
		try{
			PDDocument document = null; 
			document = PDDocument.load(new File(fileName));
			document.getClass();
			if (document.isEncrypted()) {
		        try {
		        	document.decrypt("");
		            document.setAllSecurityToBeRemoved(true);
		        }
		        catch (Exception e) {
		            throw new Exception("The document is encrypted, and we can't decrypt it.", e);
		        }
		    }
			if( !document.isEncrypted() ){
			    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			    stripper.setSortByPosition( true );
			    PDFTextStripper Tstripper = new PDFTextStripper();
			    String st = Tstripper.getText(document);
			    System.out.println("Text:"+st);
			}
			}catch(Exception e){
			    e.printStackTrace();
			}
	}
}
