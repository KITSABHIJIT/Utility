package com.test.code.util;

import com.itextpdf.text.Font;

public class PDFCellStyleUtil {
	
	public Font headerCellStyle;
	public Font bodyCellStyle;
	
	public PDFCellStyleUtil(){

		headerCellStyle = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
		bodyCellStyle = new Font(Font.FontFamily.TIMES_ROMAN, 11,Font.NORMAL);
	}

	public Font getHeaderCellStyle() {
		return headerCellStyle;
	}

	public Font getBodyCellStyle() {
		return bodyCellStyle;
	}

	
}
