package com.test.code.report;

import java.util.Date;

import com.test.code.extract.DataExtractor;
import com.test.code.util.FileUtil;
import com.test.code.util.PropertiesUtil;

public class ReportHtml {
	private static final String htmlTemplateFilePath = "./config/htmlTemplate";
	private static final String htmlTemplateMinFilePath = "./config/htmlTemplateMin";
	private static final String htmlTableTemplateFilePath = "./config/htmlTableTemplate";
	private static final String lossGainTemplateFilePath = "./config/lossGainTemplate";
	private static final String comparisionTemplateFilePath = "./config/comparisionTemplate";
	public static void generateReport(){
		
		String content=FileUtil.getStringFromFile(htmlTemplateFilePath);
		String lossGainContent=FileUtil.getStringFromFile(lossGainTemplateFilePath);
		
		content=content.replace("<TOTAL_EARNING>",String.valueOf(DataExtractor.getTotalExpense(PropertiesUtil.getProperty("TOTAL_EARNING"))));
		content=content.replace("<TOTAL_EXPENSE>",String.valueOf(DataExtractor.getTotalExpense(PropertiesUtil.getProperty("TOTAL_EXPENSE"))));
		content=content.replace("<TOTAL_AVG_EXPENSE>",String.valueOf(DataExtractor.getTotalExpense(PropertiesUtil.getProperty("TOTAL_AVG_EXPENSE"))));
		content=content.replace("<TOTAL_AVG_EARNING>",String.valueOf(DataExtractor.getTotalExpense(PropertiesUtil.getProperty("TOTAL_AVG_EARNING"))));
		content=content.replace("<DONUT_DATA>",(DataExtractor.getDrillDownJsonData(PropertiesUtil.getProperty("JSON_PAYMENT"),false,PropertiesUtil.getProperty("JSON_PAYMENT_DRILL_DOWN")).toString()));
		content=content.replace("<LINE_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_EXPENSE_DATE"),false).toString()));
		content=content.replace("<PIE_DATA>",(DataExtractor.getDrillDownJsonData(PropertiesUtil.getProperty("JSON_CATEGORY"),false,PropertiesUtil.getProperty("JSON_CATEGORY_DRILL_DOWN")).toString()));
		content=content.replace("<BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_EXPENSE_MONTH"),false).toString()));
		content=content.replace("<PERIOD_BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_PERIOD_BAR_EXPENSE_MONTH"),true).toString()));
		content=content.replace("<PERIOD_BAR_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_PERIOD_BAR_EXPENSE_MONTH"),true).toString()));
		//content=content.replace("<PERIOD_MULTIPLE_AXES_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_PERIOD_LINE_EXPENSE_DAILY"),true).toString()));
		
		lossGainContent=lossGainContent.replace("<LOSS_GAIN_DATA>",(DataExtractor.getJsonData(PropertiesUtil.getProperty("JSON_LOSS_GAIN_MONTH"),false).toString()));
		
		FileUtil.deleteFile(PropertiesUtil.getProperty("excelPath")+"/report.html");
		FileUtil.writeToFile(content, PropertiesUtil.getProperty("excelPath")+"/report.html");
		
		FileUtil.deleteFile(PropertiesUtil.getProperty("excelPath")+"/lossGain.html");
		FileUtil.writeToFile(lossGainContent, PropertiesUtil.getProperty("excelPath")+"/lossGain.html");

	}

	public static void generateReportMin(Date startDate, Date endDate,String fileName){
		FileUtil.deleteFile(fileName);
		String content=FileUtil.getStringFromFile(htmlTemplateMinFilePath);
		content=content.replace("<TOTAL_EARNING>",String.valueOf(DataExtractor.getTotalExpense(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("EARNING_TOTAL"), startDate, endDate))));
		content=content.replace("<TOTAL_EXPENSE>",String.valueOf(DataExtractor.getTotalExpense(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("EXPENSE_TOTAL"), startDate, endDate))));
		content=content.replace("<DONUT_DATA>",(DataExtractor.getDrillDownJsonData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_PAYMENT_MIN"), startDate, endDate),false,DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_PAYMENT_MIN_DRILL_DOWN"), startDate, endDate)).toString()));
		content=content.replace("<LINE_DATA>",(DataExtractor.getJsonData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_EXPENSE_DATE_MIN"), startDate, endDate),false).toString()));
		content=content.replace("<PIE_DATA>",(DataExtractor.getDrillDownJsonData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_CATEGORY_MIN"), startDate, endDate),false,DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("JSON_CATEGORY_MIN_DRILL_DOWN"), startDate, endDate)).toString()));
		FileUtil.deleteFile(fileName+".html");
		FileUtil.writeToFile(content, fileName+".html");

	}
	
	public static void generateTabularReport(){

		String content=FileUtil.getStringFromFile(htmlTableTemplateFilePath);
		content=content.replace("<TABULAR_DATA>",(DataExtractor.getJsonArrayData(PropertiesUtil.getProperty("EXPENSE_RECORDS_TABLE"),false).toString()));
		FileUtil.deleteFile(PropertiesUtil.getProperty("excelPath")+"/TabularReport.html");
		FileUtil.writeToFile(content, PropertiesUtil.getProperty("excelPath")+"/TabularReport.html");
		
		content=FileUtil.getStringFromFile(comparisionTemplateFilePath);
		content=content.replace("<TABULAR_DATA>",(DataExtractor.getJsonArrayData(PropertiesUtil.getProperty("COMPARISION_RECORDS_TABLE"),false).toString()));
		FileUtil.deleteFile(PropertiesUtil.getProperty("excelPath")+"/ComparisionReport.html");
		FileUtil.writeToFile(content, PropertiesUtil.getProperty("excelPath")+"/ComparisionReport.html");

	}
}
