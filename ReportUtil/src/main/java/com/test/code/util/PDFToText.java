package com.test.code.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
	private static String sourcePDFDirRia=PropertiesUtil.getProperty("RIA_PDF_SOURCE_RECEIPTS");
	private static String sourceImageDirRia=PropertiesUtil.getProperty("RIA_IMAGE_SOURCE_RECEIPTS");
	private static String sourceFeedDirRia=PropertiesUtil.getProperty("RIA_FEED_SOURCE_RECEIPTS");
	private static String sourceDirXoom=PropertiesUtil.getProperty("XOOM_SOURCE_RECEIPT");
	private static String excelFilePath=PropertiesUtil.getProperty("RIA_PEPORT");

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
	public static void processXoomReceipts(String sourceDirectory,List<List<Object>> list){
		try {
			File _folder = new File(sourceDirectory); 
			File[] filesInFolder;
			filesInFolder = _folder.listFiles(); 
			for (File string : filesInFolder){ 
				if(!".DS_Store".equals(string.getName())) {
					String st = getTextFromPDF(string.getAbsolutePath());
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
						tempXoom.add(transferred);
						i=i+4;
						double received=Double.parseDouble(myList.get(i).substring(0, myList.get(i).indexOf("INR")-1));
						tempXoom.add("$1.00 USD = "+StringUtil.getTwoDecimal(received/transferred)+" INR");
						tempXoom.add(received);
						i=i+1;
						list.add(tempXoom);
					}
				}
			}
		}catch(Exception e){
			System.out.println("Error out file "+sourceDirectory);
			e.printStackTrace();
		}
	}


	public static void processRiaPDFReceipts(String sourceDirectory,List<List<Object>> list){
		File _folder = new File(sourceDirectory); 
		File[] filesInFolder;
		filesInFolder = _folder.listFiles(); 
		for (File string : filesInFolder){ 
			if(!".DS_Store".equals(string.getName())) {
				String pdfData = getTextFromPDF(string.getAbsolutePath());
				list.add(extractRiaData(pdfData));
				System.out.println(string.getName()+ " appended Successfully.");
			}
		}
	}

	public static void processRiaImageReceipts(String sourceDirectory,List<List<Object>> list){
		File _folder = new File(sourceDirectory); 
		File[] filesInFolder;
		filesInFolder = _folder.listFiles(); 
		for (File string : filesInFolder){ 
			if(!".DS_Store".equals(string.getName())) {
				String imageData = ImageToText.crackImage(string.getAbsolutePath());
				list.add(extractRiaImageData(imageData));
				System.out.println(string.getName()+ " appended Successfully.");
			}
		}
	}

	public static void processRiaFeedReceipts(String sourceDirectory,List<List<Object>> list){
		File _folder = new File(sourceDirectory); 
		File[] filesInFolder;
		filesInFolder = _folder.listFiles(); 
		for (File string : filesInFolder){ 
			if(!".DS_Store".equals(string.getName())) {
				for(String data:FileUtil.readFromFile(string.getAbsolutePath()))
					list.add(extractRiaFeedData(data));
				System.out.println(string.getName()+ " appended Successfully.");
			}
		}
	}

	public static double getTotal(int index,List<List<Object>> list) {
		double total=0;

		for(List<Object> obj:list) {
			total+=(Double)obj.get(index);
		}

		return total;
	}

	public static List<Object> getYearTotal(int index,List<List<Object>> list,List<Object> yearList) {
		List<Object> totalList=new ArrayList<Object>();
		for(Object year:yearList) {
			double total=0;
			for(List<Object> obj:list) {
				if(Integer.parseInt((String)year)==DateUtil.getYear((Date)obj.get(2))) {
					total+=(Double)obj.get(index);
				}
			}
			totalList.add(Double.parseDouble(StringUtil.getTwoDecimal(total)));
		}
		return totalList;
	}

	public static List<Object> extractRiaData(String data) {
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

	public static List<Object> extractRiaFeedData(String data) {
		List<Object> tempList=new ArrayList<Object>();
		List<String> myList = new ArrayList<String>(Arrays.asList(data.split("[|]")));
		try {
			tempList.add("Ria Money");
			tempList.add(myList.get(0));
			tempList.add(DateUtil.getSQLDate(DateUtil.getSomeDate(myList.get(1), "MM/dd/yyyy hh:mm:ss a")));
			tempList.add(myList.get(2));
			tempList.add(myList.get(3));
			tempList.add(Double.parseDouble(myList.get(4).replaceAll(",", "")));
			tempList.add(myList.get(5));
			tempList.add(Double.parseDouble(myList.get(6).replaceAll(",", "")));
		}catch(Exception e){
			System.out.println("Error out Data "+data);
			e.printStackTrace();
		}
		return tempList;
	}

	public static List<Object> extractRiaImageData(String data) {
		String [] temp=null;
		String to=null;
		List<Object> tempList=new ArrayList<Object>();
		List<String> myList = new ArrayList<String>(Arrays.asList(data.split("\n")));
		try {
			for(int i=0;i<myList.size();i++) {
				temp=myList.get(i).split(" ");
				if(myList.get(i).startsWith("Order No")) {
					tempList.add("Ria Money");
					tempList.add(temp[2]);
				}
				if(myList.get(i).startsWith("Transaction Date")) {
					tempList.add(DateUtil.getSQLDate(DateUtil.getSomeDate(myList.get(i).substring(myList.get(i).indexOf(": ")+2,myList.get(i).indexOf("Date Available:")-1), "MM/dd/yyyy hh:mm:ss a")));
				}
				if(myList.get(i).startsWith("SENDER RECIPIENT")) {
					i=i+1;
					temp=myList.get(i).split(" ");
					tempList.add(temp[0]+" "+temp[1]);
					to="";
					for(int j=2;j<myList.get(i).split(" ").length;j++) {
						to=("".equals(to))?temp[j]:to+" "+temp[j];
					}
					tempList.add(to);
				}
				if(myList.get(i).startsWith("Trarsfer Anount")) {
					double total=Double.parseDouble(temp[2].replaceAll(",", ""));
					total=total/100;
					tempList.add(total);
					tempList.add(myList.get(i).substring(myList.get(i).indexOf("$")));
				}
				if(myList.get(i).startsWith("Total")) {
					double total=Double.parseDouble(myList.get(i).substring(myList.get(i).indexOf("Recipient ")+10,myList.get(i).indexOf("INR")-1));
					total=total/100;
					tempList.add(total);

				}


			}
		}catch(Exception e){
			System.out.println("Error out Data "+data);
			e.printStackTrace();
		}
		return tempList;
	}

	public static List<Object> getYears(List<List<Object>> list){
		Date endDate=(Date)list.get(0).get(2);
		Date startDate=(Date)list.get(list.size()-1).get(2);
		DateFormat formater = new SimpleDateFormat("yyyy");
		List<Object> result=new ArrayList<Object>();
		Calendar beginCalendar = Calendar.getInstance();
		Calendar finishCalendar = Calendar.getInstance();
		beginCalendar.setTime(startDate);
		finishCalendar.setTime(endDate);
		while (beginCalendar.before(finishCalendar)) {
			String date =     formater.format(beginCalendar.getTime()).toUpperCase();
			result.add(date);
			beginCalendar.add(Calendar.YEAR, 1);
		}
		return result;
	}

	public static List<List<Object>> generateRiaReports(){
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> headerList=new ArrayList<Object>();
		List<Object> yearList=new ArrayList<Object>();
		List<Object> yearWiseDollarTotal=new ArrayList<Object>();
		List<Object> yearWiseINRTotal=new ArrayList<Object>();
		headerList.add("Provider");
		headerList.add("Order No");
		headerList.add("Transaction Date");
		headerList.add("Sender");
		headerList.add("Receipent");
		headerList.add("Transfer Amount($)");
		headerList.add("Transfer Rate");
		headerList.add("Amount Received(INR)");
		FileUtil.deleteFile(excelFilePath);
		processXoomReceipts(sourceDirXoom,list);
		processRiaPDFReceipts(sourcePDFDirRia,list);
		//processRiaImageReceipts(sourceImageDirRia,list);
		processRiaFeedReceipts(sourceFeedDirRia,list);

		HSSFWorkbook workbook = new HSSFWorkbook();
		ExcelCellStyleUtil cellStyleUtil=new ExcelCellStyleUtil(workbook);
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Money Transfer");

		List<List<Object>> totalList=new ArrayList<List<Object>>();
		List<Object> headertotalList=new ArrayList<Object>();
		List<Object> totalDataList=new ArrayList<Object>();
		headertotalList.add("Total Transferred Amount($)");
		headertotalList.add("Total Received Amount(INR)");
		totalDataList.add(getTotal(5,list));
		totalDataList.add(getTotal(7,list));
		totalList.add(headertotalList);
		totalList.add(totalDataList);

		TableData tableTotalData=new TableData();
		tableTotalData.setColumnPosition(2);
		tableTotalData.setRowPosition(2);
		tableTotalData.setReportData(totalList);
		ReportExcel.writeTabularData(workbook, cellStyleUtil, sheet, tableTotalData);

		TableData tableData=new TableData();
		tableData.setColumnPosition(2);
		tableData.setRowPosition(11);
		Collections.sort(list, new Comparator<List<Object>>() {
			public int compare(List<Object> o1, List<Object> o2) {
				return ((Date)o2.get(2)).compareTo((Date)(o1.get(2)));
			}
		});
		
		List<List<Object>> totalYearList=new ArrayList<List<Object>>();
		yearList=getYears(list);
		yearWiseDollarTotal=getYearTotal(5,list,yearList);
		yearWiseINRTotal=getYearTotal(7,list,yearList);
		totalYearList.add(yearList);
		totalYearList.add(yearWiseDollarTotal);
		totalYearList.add(yearWiseINRTotal);
		TableData tableYearTotalData=new TableData();
		tableYearTotalData.setColumnPosition(2);
		tableYearTotalData.setRowPosition(6);
		tableYearTotalData.setReportData(totalYearList);
		ReportExcel.writeTabularData(workbook, cellStyleUtil, sheet, tableYearTotalData);
		
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
		return list;
	}
}
