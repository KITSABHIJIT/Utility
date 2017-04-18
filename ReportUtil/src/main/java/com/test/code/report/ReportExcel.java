package com.test.code.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.test.code.util.PropertiesUtil;

public class ReportExcel {

	public static void writeExcel(Map<String,List<List<Object>>> data, String excelFilePath){
		Workbook workbook = new HSSFWorkbook();

		Set<Entry<String, List<List<Object>>>> mapData=data.entrySet();
		for(Entry<String, List<List<Object>>> dataSet:mapData){
			Sheet sheet = workbook.createSheet(dataSet.getKey());
			int rowCount = 0;
			for (List<Object> aBook : dataSet.getValue()) {
				Row row = sheet.createRow(++rowCount);
				int columnCount = 0;
				for (Object field : aBook) {
					Cell cell = row.createCell(++columnCount);
					CellStyle cellStyle = workbook.createCellStyle();
				
					sheet.autoSizeColumn((short) (columnCount));//Set word wrap
					
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					
					if(rowCount==1){
						Font font = workbook.createFont();//Create font
						font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
						cellStyle.setFont(font);//set it to bold
						cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					}
					
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					} else if (field instanceof Double) {
						cell.setCellValue((Double) field);
					} else if (field instanceof Date) {
						CreationHelper createHelper = workbook.getCreationHelper();
						cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(PropertiesUtil.getProperty("excelDataFormat")));
						cell.setCellValue((Date) field);
					}else if (field instanceof BigDecimal) {
						cell.setCellValue(((BigDecimal) field).doubleValue());
					}
					cell.setCellStyle(cellStyle);//Apply style to cell
				}
			}
			try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
				workbook.write(outputStream);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("File generated: "+excelFilePath);
	}
}
