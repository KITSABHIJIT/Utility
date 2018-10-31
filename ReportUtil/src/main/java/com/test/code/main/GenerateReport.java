package com.test.code.main;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.test.code.extract.DataExtractor;
import com.test.code.pojo.ReportData;
import com.test.code.report.ReportExcel;
import com.test.code.report.ReportHtml;
import com.test.code.util.DateUtil;
import com.test.code.util.FileUtil;
import com.test.code.util.PDFToText;
import com.test.code.util.PropertiesUtil;

public class GenerateReport {
	final static Logger logger = Logger.getLogger(GenerateReport.class);
	public static void main (String ...strings) throws ParseException{
		long startTime=new  Date().getTime();
		
		
		Date startDate=DateUtil.getSomeDate("20180601", "yyyyMMdd");
		Date endDate=DateUtil.getSomeDate("20181031", "yyyyMMdd");
		List<Date> dateList =DateUtil.getDatesFromDateRange(startDate, endDate, "MONTHLY");
		int counter=0;
		logger.info("Process started...");
		for(int i=1;i<dateList.size();i++){
			if(counter%2==0){
			// Extract Data
				Map<String,ReportData> data=DataExtractor.getReportData(dateList.get(i-1), dateList.get(i));
				StringBuilder exportFilename =new StringBuilder(PropertiesUtil.getProperty("excelPath"));
				exportFilename.append(System.getProperty("file.separator"))
				.append(DateUtil.getDateToString(dateList.get(i), "yyyy"))
				.append(System.getProperty("file.separator"))
				.append(DateUtil.getDateToString(dateList.get(i), "MMM"));
				FileUtil.createDirectory(exportFilename.toString());
				// Generate Report
				ReportExcel.writeExcel(data,exportFilename.toString()+System.getProperty("file.separator")+DateUtil.getDateToString(dateList.get(i), "MMM")+PropertiesUtil.getProperty("excelFileExtn"));
				ReportHtml.generateReportMin(dateList.get(i-1), dateList.get(i),exportFilename.toString()+System.getProperty("file.separator")+DateUtil.getDateToString(dateList.get(i), "MMM"));
			}
			counter++;
		}
		
		ReportHtml.generateReport();
		ReportHtml.generateTabularReport();
		PDFToText.generateRiaReports();
		
		long endTime=new  Date().getTime();
		logger.info("Process ended...");
		logger.info("Total time taken: "+DateUtil.getHrMinSec(endTime-startTime));
	}
}