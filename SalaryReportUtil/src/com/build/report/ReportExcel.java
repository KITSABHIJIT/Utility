package com.build.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.build.bean.TableData;
import com.build.util.ExcelCellStyleUtil;
import com.build.util.FileUtil;

public class ReportExcel {
	public static void writeExcel(TableData data, String excelFilePath){
		FileUtil.deleteFile(excelFilePath);
		HSSFWorkbook workbook = new HSSFWorkbook();
		ExcelCellStyleUtil cellStyleUtil=new ExcelCellStyleUtil(workbook);
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Salary");
		writeTabularData(workbook,cellStyleUtil,sheet,data);
		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("File generated: "+excelFilePath);
	}

	public static void writeTabularData(Workbook workbook,ExcelCellStyleUtil cellStyleUtil,HSSFSheet sheet,TableData data){
		int rowCount=0;
		int startRowPosition = data.getRowPosition();

		for (List<Object> aBook : data.getReportData()) {
			Row row = sheet.createRow(startRowPosition);
			int StartColumnPosition = data.getColumnPosition();
			for (Object field : aBook) {
				Cell cell = row.createCell(StartColumnPosition);
				sheet.autoSizeColumn((short) (StartColumnPosition));//Set word wrap
				sheet.createFreezePane(1, 1);
				if(rowCount==0){
					cell.setCellStyle(cellStyleUtil.getHeaderCellStyle());
				}else{
					cell.setCellStyle(cellStyleUtil.getBodyCellStyle());
				}
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				} else if (field instanceof Double) {
					cell.setCellValue((Double) field);
					cell.setCellStyle(cellStyleUtil.getBodyNumericCellStyle());
				} else if (field instanceof Date) {
					cell.setCellValue((Date) field);
					cell.setCellStyle(cellStyleUtil.getBodyDateCellStyle());
				}else if (field instanceof BigDecimal) {
					cell.setCellValue(((BigDecimal) field).doubleValue());
				}
				StartColumnPosition++;
			}
			startRowPosition++;
			rowCount++;
		}
	}
}
