package com.build.pdf.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;



public class PDFSplitByText {
	public static final String SRC = CommonConstants.FILE_PATH+"/PODODRP_PKG000004.pdf";
	public static final String DEST = CommonConstants.FILE_PATH+"/splitpdfs";
	public static final String DEST_FILE = DEST+"/%s.pdf";
	public static final String SEARCH_INDEX="Invoice Number:";

	public static void main(String[] args) throws IOException, DocumentException {
		File source =new File(SRC);
		if(source.exists()){
			split(SRC,source);
		}else{
			System.err.print("No source file found");
		}
	}


	public static void split(String filename,File source) throws IOException, BadPdfFormatException {
		PdfReader reader = new PdfReader(filename);
		int n = reader.getNumberOfPages();
		String invoice,prevInvoice="";
		System.out.println(n);
		PdfCopy writer=null;
		Document document=null;
		File dest =new File(DEST);
		if(!dest.exists()){
			dest.mkdirs();
		}
		for (int page = 1; page <= reader.getNumberOfPages(); page++) {
			//fos.write(extractor.getTextFromPage(page).getBytes("UTF-8"));


			String data= new String(PdfTextExtractor.getTextFromPage(reader, page).getBytes("UTF-8"));
			
			if(data.indexOf(SEARCH_INDEX)>-1){
				int beginIndex=data.indexOf(SEARCH_INDEX)+SEARCH_INDEX.length()+1;
				invoice=data.substring(beginIndex, beginIndex+10).trim();
			}else{
				invoice	=source.getName();// New Name we can fetch from database.
			}
			if(page == 1){
				prevInvoice = invoice;
				document = new Document(reader.getPageSizeWithRotation(1));
				try {
					writer = new PdfCopy(document, new FileOutputStream(String.format(DEST_FILE,invoice)));
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!prevInvoice.equals(invoice)){
				if(writer != null)
					writer.close();
				if(document != null)
					document.close();
				document = new Document(reader.getPageSizeWithRotation(1));
				try {
					writer = new PdfCopy(document, new FileOutputStream(String.format(DEST_FILE,invoice)));
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				document.open();
				PdfImportedPage splitPdfs = writer.getImportedPage(reader, page);
				writer.addPage(splitPdfs);
				//System.out.println(invoice);
			}else{
				document.open();
				PdfImportedPage splitPdfs = writer.getImportedPage(reader, page);
				writer.addPage(splitPdfs);
			}

		}
		if(writer != null)
			writer.close();
		if(document != null)
			document.close();
	}
}
