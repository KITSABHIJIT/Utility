package com.build.pdf.util;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

public class PDFToText {

	public static void main(String args[]) {
		String sourceDir="C:\\Users\\royab001\\pdfUtil\\source"+System.getProperty("file.separator");
		String fileName=sourceDir+"Combined.txt";
		readPDF(sourceDir,fileName);
	} 
	
	
	public static void readPDF(String sourceDir,String fileName){
		try{
			File _folder = new File(sourceDir); 
			File[] filesInFolder; 
			filesInFolder = _folder.listFiles(); 
			for (File string : filesInFolder){ 
				PDDocument document = null; 
				document = PDDocument.load(string);
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
					FileUtil.writeToFile(st, fileName);
					System.out.println(string.getName()+ " appended Successfully.");
					//FileUtil.writeToFile("\n\n\n\n\n\n\n\n\n", fileName);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
