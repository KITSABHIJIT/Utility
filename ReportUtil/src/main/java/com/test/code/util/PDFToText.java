package com.test.code.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.test.code.pojo.TableData;
import com.test.code.report.ReportExcel;

public class PDFToText {
	private static String sourceDirRia=PropertiesUtil.getProperty("RIA_SOURCE_RECEIPTS");
	private static String sourceFileXoom=PropertiesUtil.getProperty("XOOM_SOURCE_RECEIPT");
	private static String excelFilePath=PropertiesUtil.getProperty("RIA_PEPORT");

	public static void main(String ...strings ) {
		readPDF();
	}
	public static void generateRiaReports() {
		readPDF();
	} 


	public static List<List<Object>> readPDF(){
		String currentFile=null;
		String [] temp=null;
		String to=null;
		PDDocument document = null;
		PDDocument documentXoom = null; 
		double totalTransferred=0,totalReceived=0;
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> headerList=new ArrayList<Object>();
		headerList.add("Provider");
		headerList.add("Order No");
		headerList.add("Transaction Date");
		headerList.add("Sender");
		headerList.add("Receipent");
		headerList.add("Transfer Amount($)");
		headerList.add("Transfer Rate");
		headerList.add("Amount Received(INR)");
		
		try {
			documentXoom = PDDocument.load(sourceFileXoom);
			documentXoom.getClass();
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition( true );
			PDFTextStripper Tstripper = new PDFTextStripper();
			String st = Tstripper.getText(documentXoom);
			List<String> myList = new ArrayList<String>(Arrays.asList(st.split("\n")));
			for(int i=3;i<myList.size();i++){
				List<Object> tempXoom=new ArrayList<Object>();
				tempXoom.add("Xoom");
				tempXoom.add(myList.get(i));
				if(null!=myList.get(i+4) && StringUtil.isNumeric((myList.get(i+4)).substring(0, 2))){
					i=i+4;
					String date=(myList.get(i-2)+myList.get(i-1).trim()+myList.get(i)).replaceAll(" ", "");
					tempXoom.add(DateUtil.getSQLDate(DateUtil.getSomeDate(date, "MMMMMdd,yyyyhh:mma")));
				}else {
					i=i+3;
					String date=(myList.get(i-1)+" "+myList.get(i)).replaceAll(" ", "");
					tempXoom.add(DateUtil.getSQLDate(DateUtil.getSomeDate(date, "MMMMMdd,yyyyhh:mma")));
				}
				i=i+2;
				tempXoom.add(myList.get(i-1)+" "+myList.get(i));
				tempXoom.add(myList.get(i-1)+" "+myList.get(i));
				i=i+2;
				double transferred=Double.parseDouble(myList.get(i).substring(0, myList.get(i).indexOf("USD")-1));
				totalTransferred=totalTransferred+transferred;
				tempXoom.add(transferred);
				i=i+4;
				double received=Double.parseDouble(myList.get(i).substring(0, myList.get(i).indexOf("INR")-1));
				totalReceived=totalReceived+received;
				tempXoom.add("$1.00 USD = "+StringUtil.getTwoDecimal(received/transferred)+" INR");
				tempXoom.add(received);
				i=i+1;
				list.add(tempXoom);
			}
		}catch(Exception e){
			System.out.println("Error out file "+sourceFileXoom);
			e.printStackTrace();
		}
		
		try{
			FileUtil.deleteFile(excelFilePath);
			File _folder = new File(sourceDirRia); 
			File[] filesInFolder;
			filesInFolder = _folder.listFiles(); 
			for (File string : filesInFolder){ 
				if(!".DS_Store".equals(string.getName())) {
					List<Object> tempList=new ArrayList<Object>();
					currentFile=string.getAbsolutePath();
					document = PDDocument.load(string);
					document.getClass();
					PDFTextStripperByArea stripper = new PDFTextStripperByArea();
					stripper.setSortByPosition( true );
					PDFTextStripper Tstripper = new PDFTextStripper();
					String st = Tstripper.getText(document);
					List<String> myList = new ArrayList<String>(Arrays.asList(st.split("\n")));
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
							totalTransferred=totalTransferred+total;
							tempList.add(total);
							tempList.add(line.substring(line.indexOf("$")));
						}
						if(line.startsWith("Total")) {
							double total=Double.parseDouble(temp[6].replaceAll(",", ""));
							totalReceived=totalReceived+total;
							tempList.add(total);
						}
					}
					list.add(tempList);
					System.out.println(string.getName()+ " appended Successfully.");
					document.close();
				}
			}

			HSSFWorkbook workbook = new HSSFWorkbook();
			ExcelCellStyleUtil cellStyleUtil=new ExcelCellStyleUtil(workbook);
			HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Money Transfer");

			List<List<Object>> totalList=new ArrayList<List<Object>>();
			List<Object> headertotalList=new ArrayList<Object>();
			List<Object> totalDataList=new ArrayList<Object>();
			headertotalList.add("Total Transferred Amount($)");
			headertotalList.add("Total Received Amount(INR)");
			totalDataList.add(totalTransferred);
			totalDataList.add(totalReceived);
			totalList.add(headertotalList);
			totalList.add(totalDataList);

			TableData tableTotalData=new TableData();
			tableTotalData.setColumnPosition(2);
			tableTotalData.setRowPosition(2);
			tableTotalData.setReportData(totalList);
			ReportExcel.writeTabularData(workbook, cellStyleUtil, sheet, tableTotalData);

			TableData tableData=new TableData();
			tableData.setColumnPosition(2);
			tableData.setRowPosition(6);
			Collections.sort(list, new Comparator<List<Object>>() {
		        public int compare(List<Object> o1, List<Object> o2) {
		            return ((Date)o2.get(2)).compareTo((Date)(o1.get(2)));
		        }
		    });
			list.add(0,headerList);
			tableData.setReportData(list);
			ReportExcel.writeTabularData(workbook, cellStyleUtil, sheet, tableData);


			try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
				workbook.write(outputStream);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("File generated: "+excelFilePath);
		}catch(Exception e){
			System.out.println("Error out file "+currentFile);
			e.printStackTrace();
		}
		return list;
	}
}
