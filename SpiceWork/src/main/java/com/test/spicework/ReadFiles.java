package com.test.spicework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;


public class ReadFiles {

	private static String sourcePDFDir="/Users/abhijit/Downloads/spicework";
	
	public static void main(String[] args) {
		List<List<Object>> list=new ArrayList<List<Object>>();
		processRiaPDFReceipts(sourcePDFDir,list);

	}

	public static void processRiaPDFReceipts(String sourceDirectory,List<List<Object>> list){
		File _folder = new File(sourceDirectory); 
		File[] filesInFolder;
		filesInFolder = _folder.listFiles(); 
		for (File string : filesInFolder){ 
			if(!".DS_Store".equals(string.getName())) {
				String pdfData = getTextFromPDF(string.getAbsolutePath());
				List<Object> extractData=extractData(pdfData);
				if(extractData.size()>0)
					list.add(extractData);
				System.out.println(string.getName()+ " appended Successfully.");
			}
		}
	}
	

	public static String getTextFromPDF(String fileName) {
		PDDocument document = null;
		String result=null;
		try {
			document = PDDocument.load(fileName);
			document.getClass();
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition( true );
			PDFTextStripper Tstripper = new PDFTextStripper();
			result = Tstripper.getText(document);
		}catch(Exception e){
			System.out.println("Error out file "+fileName);
			e.printStackTrace();
		}finally{
			try {
				if(null!=document) {
					document.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static List<Object> extractData(String data) {
		String [] temp=null;
		String to=null;
		List<Object> tempList=new ArrayList<Object>();
		List<String> myList = new ArrayList<String>(Arrays.asList(data.split("\n")));
		try {
			for(String line:myList) {
				temp=line.split(" ");
				if(line.startsWith("Order No")) {
					tempList.add("Ria Money");
					tempList.add(temp[2]);
				}
				if(line.startsWith("Transaction Date")) {
					tempList.add(DateUtil.getSQLDate(DateUtil.getSomeDate(line.substring(line.indexOf(": ")+2), "MM/dd/yyyy hh:mm:ss a")));
				}
				if(line.startsWith("ABHIJIT")) {
					tempList.add(temp[0]+" "+temp[1]);
					to="";
					for(int i=2;i<line.split(" ").length;i++) {
						to=("".equals(to))?temp[i]:to+" "+temp[i];
					}
					tempList.add(to);
				}
				if(line.startsWith("TANAYA")) {
					tempList.add(temp[0]+" "+temp[1]+" "+temp[2]);
					to="";
					for(int i=3;i<line.split(" ").length;i++) {
						to=("".equals(to))?temp[i]:to+" "+temp[i];
					}
					tempList.add(to);
				}
				if(line.startsWith("Transfer Amount")) {
					double total=Double.parseDouble(temp[2].replaceAll(",", ""));
					tempList.add(total);
					if(line.indexOf("$")>0) {
						tempList.add(line.substring(line.indexOf("$")));
					}
				}
				if(line.startsWith("Exchange Rate")) {
					tempList.add(line.substring(line.indexOf("$")));
				}
				if(line.startsWith("Total")) {
					double total=Double.parseDouble(temp[6].replaceAll(",", ""));
					tempList.add(total);
				}
			}
		}catch(Exception e){
			System.out.println("Error out Data "+data);
			e.printStackTrace();
		}
		return tempList;
	}
}
