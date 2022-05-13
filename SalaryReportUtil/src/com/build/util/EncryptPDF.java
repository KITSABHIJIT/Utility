package com.build.util;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;

public class EncryptPDF {

	public static void decrypt(String sourcefolder) { 
		try { 
			File _folder = new File(sourcefolder); 
			File[] filesInFolder; 
			filesInFolder = _folder.listFiles(); 
			for (File string : filesInFolder){ 
				// Load the PDF file
				PDDocument pdd = PDDocument.load(string, CommonConstants.PASSWORD);
				// removing all security from PDF file
				pdd.setAllSecurityToBeRemoved(true);
				// Save the PDF file
				pdd.save(string);
				// Close the PDF file
				pdd.close();
				System.out.println("Decryption Done..."+string.getName());
			}
		} catch(Exception e) { 
			e.printStackTrace();
		}	
	} 

	public static void main(String[] args){
		EncryptPDF.decrypt("/Users/abhijit/Desktop/TCS-PaySlips");
	}
}
