package com.test.code;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImageCracker {
	public static String crackImage(String filePath) {  
		File imageFile = new File(filePath);  
		ITesseract instance = new Tesseract();   
		try {  
			instance.setPageSegMode(3); 
			String result = instance.doOCR(imageFile);  
			return result;  
		} catch (TesseractException e) {  
			System.err.println(e.getMessage());  
			return "Error while reading image";  
		}  
	}

	public static String getTextFromPDF(String filePath) {
		String result=null;
		File image = new File(filePath);
		// recognizeTextBlocks(image.toPath());

		Tesseract tessInst = new Tesseract();
		tessInst.setDatapath("/tessdata-master/tessdata");
		try
		{
			result = tessInst.doOCR(image);
			System.out.println(result);
		}
		catch (TesseractException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}
}
