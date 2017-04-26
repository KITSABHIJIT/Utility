package com.test.code.report;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import com.test.code.util.PropertiesUtil;

public class CellStyleUtil {
	
	public CellStyle headerCellStyle;
	public CellStyle bodyCellStyle;
	public CellStyle bodyNumericCellStyle;
	public CellStyle bodyDateCellStyle;
	
	public CellStyleUtil(Workbook workbook){

		Font fontHeader = workbook.createFont();//Create font
		fontHeader.setFontName("Calibri");
		fontHeader.setFontHeightInPoints((short) 12);
		fontHeader.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
		
		Font fontBody = workbook.createFont();//Create font
		fontBody.setFontName("Calibri");
		fontBody.setFontHeightInPoints((short) 11);
		
		headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(fontHeader);//set it to bold
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		bodyDateCellStyle = workbook.createCellStyle();
		bodyDateCellStyle.setFont(fontBody);
		bodyDateCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		bodyDateCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		bodyDateCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		bodyDateCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		CreationHelper createHelper = workbook.getCreationHelper();
		bodyDateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat(PropertiesUtil.getProperty("excelDateFormat")));

		bodyNumericCellStyle = workbook.createCellStyle();
		bodyNumericCellStyle.setFont(fontBody);
		bodyNumericCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		bodyNumericCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		bodyNumericCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		bodyNumericCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//DataFormat format = workbook.createDataFormat();
		//cellStyleBodyNumeric.setDataFormat(format.getFormat(PropertiesUtil.getProperty("excelNumericFormat")));
		bodyNumericCellStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));

		bodyCellStyle= workbook.createCellStyle();
		bodyCellStyle.setFont(fontBody);
		bodyCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		bodyCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		bodyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		bodyCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
	}

	public CellStyle getHeaderCellStyle() {
		return headerCellStyle;
	}

	public CellStyle getBodyCellStyle() {
		return bodyCellStyle;
	}

	public CellStyle getBodyNumericCellStyle() {
		return bodyNumericCellStyle;
	}

	public CellStyle getBodyDateCellStyle() {
		return bodyDateCellStyle;
	}
	
}
