package com.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadReport {

	public static void main(String ...strings) {
		String googleDrivePath="/Users/abhijit/Desktop";
		//googleDrivePath="/Users/abhijit/Desktop";
		String inputPath=googleDrivePath+"/58xOpenDemand_v2 01.16.2023.xlsx";
		String outputPath=googleDrivePath+"/output.xlsx";

		OpenDemandStock odStocks= getOpenDemands(inputPath);
		ReportExcel.writeExcelXlsx(getFieldsData(updateOpenDemandAvailability(odStocks)), outputPath);

	}

	public static OpenDemandStock getOpenDemands(String file){
		Map<Long,Long> stockAvailability=new HashMap<Long,Long>();
		List<OpenDemand> records = new ArrayList<OpenDemand>();
		int recordCount=0;
		try  
		{  
			FileInputStream fis = new FileInputStream(new File(file));   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(1);     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			while (itr.hasNext())                 
			{  
				Row row = itr.next();  
				if(row.getRowNum()>1) {
					OpenDemand od=new OpenDemand();
					Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
					while (cellIterator.hasNext())   
					{  
						Cell cell = cellIterator.next(); 
						switch (cell.getColumnIndex()) {

						case 0:
							od.setSite((int) cell.getNumericCellValue());
							break; 
						case 1:
							od.setSoldToName(cell.getStringCellValue());
							if("CrossChannel".equals(od.getSoldToName())) {
								od.setCrossChannel(true);
							}
							break;
						case 2:
							od.setTeamMember(cell.getStringCellValue());
							break;
						case 5:
							od.setArticleNumber((long) cell.getNumericCellValue());
							break;
						case 6:
							od.setArticleDescripion(cell.getStringCellValue());
							break;
						case 9:
							od.setVariance((int) cell.getNumericCellValue());
							break;
						case 13:
							od.setAvailableQty((long) cell.getNumericCellValue());
							break;
						case 15:
							switch (cell.getCachedFormulaResultType()) {
							case Cell.CELL_TYPE_NUMERIC:
								od.setDaysAged((int)cell.getNumericCellValue());
								break;
							case Cell.CELL_TYPE_ERROR:
								od.setDaysAged(1);
								break;
							}
							break;
						default: 
						}
					}
					od.setSlNo(++recordCount);
					System.out.println(od.toString()); 
					records.add(od);
					if(!(stockAvailability.containsKey(od.getArticleNumber()) && od.getAvailableQty()==0))
						stockAvailability.put(od.getArticleNumber(), od.getAvailableQty());
				}
			}  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
		return new OpenDemandStock(stockAvailability, records);

	}

	public static List<OpenDemand> updateOpenDemandAvailability(OpenDemandStock odStocks){
		List<OpenDemand> result=new ArrayList<OpenDemand>();
		Map<Long,Long> availableStock=odStocks.getStockAvaialability();

		List<OpenDemand> tempList=odStocks.getOpenDemandRecords();
		tempList.sort(Comparator.comparing(OpenDemand::isCrossChannel,Comparator.reverseOrder())
				.thenComparing(OpenDemand::getArticleNumber)
				.thenComparing(OpenDemand::getDaysAged,Comparator.reverseOrder()));
		for(OpenDemand od:tempList) {
			OpenDemand temp=od;
			if(availableStock.get(od.getArticleNumber())>=od.getVariance()){
				temp.setUnitsToFill(od.getVariance());
				availableStock.put(od.getArticleNumber(), availableStock.get(od.getArticleNumber())-od.getVariance());
			}else {
				temp.setUnitsToFill(availableStock.get(od.getArticleNumber()).intValue());
				availableStock.put(od.getArticleNumber(), 0L);
			}
			result.add(temp);
		}
		return result;
	}

	public static boolean isBlankOrEmpty(Object obj){
		if(null==obj){
			return true;
		}else{
			String temp=obj.toString();
			if("".equals(temp.trim())){
				return true;
			}else{
				return false;
			}
		}
	}

	public static String replaceNewLineWithSpace(Object obj){
		if(null==obj){
			return null;
		}else{
			String temp=obj.toString();
			return temp.replaceAll("(\\t|\\r?\\n)+", " ");
		}
	}

	public static Date getDate(final String str, final String inputFormat)
			throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		return sdf.parse(str);
	}

	public static int countWordsUsingStringTokenizer(String sentence, String delemeter) {
		if (sentence == null || sentence.isEmpty()) {
			return 0;
		}

		String[] words = sentence.split(delemeter);
		return words.length-1;
	}

	public static List<List<Object>> getFieldsData(List<OpenDemand> openDemands){
		List<List<Object>> data=new ArrayList<List<Object>>();
		List<Object> header=new ArrayList<Object>();
		header.add("Site");
		header.add("Sold-to Name");
		header.add("Sales Group (Team Member)");
		header.add("Article Number");
		header.add("Article Description");
		header.add("Variance");
		header.add("Avail OH+IT QTY For Delivery");
		header.add("Days Aged");
		header.add("Units to fill");
		data.add(header);
		Collections.sort(openDemands, (OpenDemand a1, OpenDemand a2) -> a1.slNo-a2.slNo);
		for(OpenDemand openDemand: openDemands) {
			List<Object> lines=new ArrayList<Object>();
			lines.add(openDemand.getSite());
			lines.add(openDemand.getSoldToName());
			lines.add(openDemand.getTeamMember());
			lines.add(openDemand.getArticleNumber());
			lines.add(openDemand.getArticleDescripion());
			lines.add(openDemand.getVariance());
			lines.add(openDemand.getAvailableQty());
			lines.add(openDemand.getDaysAged());
			lines.add(openDemand.getUnitsToFill());
			data.add(lines);
		}

		return data;
	}

}
