package com.build.pdf.util;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFMergerUtility;

public class MergePDF {
	public static void main(String args[]) {
		String sourceDir="/Users/abhijit/Library/CloudStorage/GoogleDrive-cemkabhijit@gmail.com/My Drive/personal details/Medical Docs/Maa/Tata-Medical/HISTOPATHOLOGY-Reports/";
		String destDir="/Users/abhijit/Library/CloudStorage/GoogleDrive-cemkabhijit@gmail.com/My Drive/personal details/Medical Docs/Maa/Tata-Medical/HISTOPATHOLOGY-Reports/";
		decryptcombine(sourceDir,destDir,"HISTOPATHOLOGY-Report.pdf");
	} 
	
	public static void decryptcombine(String sourcefolder,String destFolder,String mergedFile) { 
		try { 
			PDFMergerUtility mergePdf = new PDFMergerUtility(); 
			File _folder = new File(sourcefolder); 
			File[] filesInFolder; 
			filesInFolder = _folder.listFiles(); 
			for (File string : filesInFolder){ 
				copyPDF(string,destFolder);
			}
			File _destFolder = new File(destFolder); 
			File[] destFilesInFolder; 
			destFilesInFolder = _destFolder.listFiles(); 
			Arrays.sort(destFilesInFolder,Collections.reverseOrder());
			for (File string : destFilesInFolder){ 
				if(!".DS_Store".equals(string.getName())) {
				mergePdf.addSource(string);	
				System.out.println("Document appended: " +string.getName());
				}
			}
			mergePdf.setDestinationFileName(destFolder+mergedFile); 
			mergePdf.mergeDocuments(); 
		} catch(Exception e) { 
			e.printStackTrace();
		}	
	} 
	
	public static void combine(String sourcefolder, String mergedFile) { 
		try { 
			PDFMergerUtility mergePdf = new PDFMergerUtility(); 
			File _folder = new File(sourcefolder); 
			File[] filesInFolder; 
			filesInFolder = _folder.listFiles(); 
			for (File string : filesInFolder){ 
				if(!".DS_Store".equals(string.getName())) {
					mergePdf.addSource(string);	
					System.out.println("Document appended: " +string.getName());
				}
			}
			mergePdf.setDestinationFileName(sourcefolder+mergedFile); 
			mergePdf.mergeDocuments(); 
		} catch(Exception e) { 
			e.printStackTrace();
		}	
	} 
	
	public void createNew() {
		PDDocument document = null;
		try { 
			String filename="test.pdf"; 
			document=new PDDocument(); 
			PDPage blankPage = new PDPage(); 
			document.addPage( blankPage ); 
			document.save( filename ); 
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
	
	public static void copyPDF(File input,String copyDir){
		try{
			PDDocument document = null; 
			document = PDDocument.load(input);
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
			document.save(copyDir+System.getProperty("file.separator")+input.getName()); 
			}catch(Exception e){
			    e.printStackTrace();
			}
	}
	
}
