package com.build.pdf.util;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReduceSize {

	public static void main(String[] args) {
		String sourceFile="C:\\Users\\home\\Desktop\\Petition-2021.pdf";
		String destFile="C:\\\\Users\\\\home\\\\Desktop\\\\Petition-2021-reduced.pdf";
		try {
			compressPdf(sourceFile,destFile);
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void compressPdf(String src, String dest) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(src);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest), PdfWriter.VERSION_1_5);
		stamper.getWriter().setCompressionLevel(9);
		int total = reader.getNumberOfPages() + 1;
		for (int i = 1; i < total; i++) {
			reader.setPageContent(i, reader.getPageContent(i));
		}
		stamper.setFullCompression();
		stamper.close();
		reader.close();
	}
}
