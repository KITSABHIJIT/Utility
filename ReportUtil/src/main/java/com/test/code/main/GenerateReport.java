package com.test.code.main;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.test.code.extract.DataExtractor;
import com.test.code.pojo.ReportData;
import com.test.code.report.ReportExcel;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;

public class GenerateReport {
	final static Logger logger = Logger.getLogger(GenerateReport.class);
	public static void main (String ...strings) throws ParseException{
		long startTime=new  Date().getTime();
		Date startDate=DateUtil.getSomeDate("20150601", "yyyyMMdd");
		Date endDate=DateUtil.getSomeDate("20170431", "yyyyMMdd");
		List<Date> dateList =DateUtil.getDatesFromDateRange(startDate, endDate, "Monthly");
		int counter=0;
		logger.info("Process started...");
		for(int i=1;i<dateList.size();i++){
			if(counter%2==0){
			// Extract Data
				Map<String,ReportData> data=DataExtractor.getReportData(dateList.get(i-1), dateList.get(i));
				StringBuilder exportFilename =new StringBuilder(PropertiesUtil.getProperty("excelPath"));
				exportFilename.append(DateUtil.getDateToString(dateList.get(i), "yyyy-MMM"))
				.append(PropertiesUtil.getProperty("excelFileExtn"));
				// Generate Report
				ReportExcel.writeExcel(data,exportFilename.toString());
			}
			counter++;
		}	
		long endTime=new  Date().getTime();
		logger.info("Process ended...");
		logger.info("Total time taken: "+DateUtil.getHrMinSec(endTime-startTime));
	}
}