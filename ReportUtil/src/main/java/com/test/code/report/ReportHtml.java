package com.test.code.report;

import java.util.Date;

import com.test.code.extract.DataExtractor;
import com.test.code.util.FileUtil;
import com.test.code.util.PropertiesUtil;

public class ReportHtml {
	private static final String htmlTemplateFilePath = "./config/htmlTemplate";
	private static final String htmlTemplateMinFilePath = "./config/htmlTemplateMin";
	private static final String htmlTableTemplateFilePath = "./config/htmlTableTemplate";
	public static void generateReport(){

		String content=FileUtil.getStringFromFile(htmlTemplateFilePath);
		
		content=content.replace("<TOTAL_EXPENSE>",String.valueOf(DataExtractor.getTotalExpense(PropertiesUtil.getProperty("TOTAL_EXPENSE"))));
		content=content.replace("<DONUT_DATA>",(DataExtractor.getDrillDownJsonData(PropertiesUtil.getProperty("JSON_PAYMENT"),false,PropertiesUtil.getProperty("JSON_PAYMENT_DRILL_DOWN")).toString()));
		content=content.replace("<LINE_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_EXPENSE_DATE"),false).toString()));
		content=content.replace("<PIE_DATA>",(DataExtractor.getDrillDownJsonData(PropertiesUtil.getProperty("JSON_CATEGORY"),false,PropertiesUtil.getProperty("JSON_CATEGORY_DRILL_DOWN")).toString()));
		content=content.replace("<BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_EXPENSE_MONTH"),false).toString()));
		content=content.replace("<PERIOD_BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_PERIOD_BAR_EXPENSE_MONTH"),true).toString()));
		content=content.replace("<PERIOD_BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_PERIOD_BAR_EXPENSE_MONTH"),true).toString()));
		FileUtil.writeToFile(content, PropertiesUtil.getProperty("excelPath")+"/report.html");

	}

	public static void generateReportMin(Date startDate, Date endDate,String fileName){

		String content=FileUtil.getStringFromFile(htmlTemplateMinFilePath);
		content=content.replace("<TOTAL_EXPENSE>",String.valueOf(DataExtractor.getTotalExpense(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("TOTAL_EXPENSE_MIN"), startDate, endDate))));
		content=content.replace("<DONUT_DATA>",(DataExtractor.getDrillDownJsonData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_PAYMENT_MIN"), startDate, endDate),false,DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_PAYMENT_MIN_DRILL_DOWN"), startDate, endDate)).toString()));
		content=content.replace("<LINE_DATA>",(DataExtractor.getJsonData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_EXPENSE_DATE_MIN"), startDate, endDate),false).toString()));
		content=content.replace("<PIE_DATA>",(DataExtractor.getDrillDownJsonData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_CATEGORY_MIN"), startDate, endDate),false,DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_CATEGORY_MIN_DRILL_DOWN"), startDate, endDate)).toString()));
		FileUtil.writeToFile(content, fileName+".html");

	}
	
	public static void generateTabularReport(){

		String content=FileUtil.getStringFromFile(htmlTableTemplateFilePath);
		content=content.replace("<TABULAR_DATA>",(DataExtractor.getJsonArrayData(PropertiesUtil.getProperty("EXPENSE_RECORDS_TABLE"),false).toString()));
		FileUtil.writeToFile(content, PropertiesUtil.getProperty("excelPath")+"/TabularReport.html");

	}
}
