package com.test.code.report;

import com.test.code.extract.DataExtractor;
import com.test.code.util.FileUtil;
import com.test.code.util.PropertiesUtil;

public class ReportHtml {
	private static final String filePath = "./config/htmlTemplate";
	public static void generateReport(){
		String content=FileUtil.getStringFromFile(filePath);
		content=content.replaceAll("<PIE_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_PAYMENT"),false).toString()));
		content=content.replaceAll("<LINE_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_EXPENSE_DATE"),false).toString()));
		content=content.replaceAll("<DONUT_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_CATEGORY"),false).toString()));
		content=content.replaceAll("<BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_EXPENSE_MONTH"),false).toString()));
		content=content.replaceAll("<PERIOD_BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_PERIOD_BAR_EXPENSE_MONTH"),true).toString()));
		content=content.replaceAll("<TOTAL_EXPENSE>",String.valueOf(DataExtractor.getTotalExpense(PropertiesUtil.getProperty("TOTAL_EXPENSE"))));
		FileUtil.writeToFile(content, PropertiesUtil.getProperty("excelPath")+"/report.html");
	}
}
