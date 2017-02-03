package com.build.pdf.util;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

public class PDFSplitByPage {
	public void splitPDFFile(String fileName, String dest){
		try {
			String inFile = fileName.toLowerCase();
			System.out.println ("Reading " + inFile);
			PdfReader reader = new PdfReader(inFile);
			int n = reader.getNumberOfPages();
			System.out.println ("Number of pages : " + n);
			int i = 0;
			while ( i < n ) {
				String outFile = inFile.substring(0, inFile.indexOf(".pdf"))
						+ "-" + String.format("%03d", i + 1) + ".pdf";
				outFile=dest+System.getProperty("file.separator");
				System.out.println ("Writing " + outFile);
				Document document = new Document(reader.getPageSizeWithRotation(1));
				PdfCopy writer = new PdfCopy(document, new FileOutputStream(outFile));
				document.open();
				PdfImportedPage page = writer.getImportedPage(reader, ++i);
				writer.addPage(page);
				document.close();
				writer.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
