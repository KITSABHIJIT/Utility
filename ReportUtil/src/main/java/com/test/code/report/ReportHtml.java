package com.test.code.report;

import com.test.code.extract.DataExtractor;
import com.test.code.util.FileUtil;
import com.test.code.util.PropertiesUtil;

public class ReportHtml {
	private static final String filePath = "./config/htmlTemplate";
	public static void generateReport(){
		String content=FileUtil.getStringFromFile(filePath);
		content=content.replaceAll("<PIE_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_PAYMENT")).toString()));
		content=content.replaceAll("<LINE_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_EXPENSE_DATE")).toString()));
		content=content.replaceAll("<DONUT_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_CATEGORY")).toString()));
		content=content.replaceAll("<BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_EXPENSE_MONTH")).toString()));
		FileUtil.writeToFile(content, PropertiesUtil.getProperty("excelPath")+"/report.html");
	}
}
