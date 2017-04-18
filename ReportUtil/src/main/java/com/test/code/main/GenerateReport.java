package com.test.code.main;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.test.code.extract.DataExtractor;
import com.test.code.report.ReportExcel;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;

public class GenerateReport {

	public static void main (String ...strings) throws ParseException{
		Date startDate=DateUtil.getSomeDate("20150601", "yyyyMMdd");
		Date endDate=DateUtil.getSomeDate("20170418", "yyyyMMdd");
		List<Date> dateList =DateUtil.getDatesFromDateRange(startDate, endDate, "MONTHLY");
		int counter=0;
		for(int i=1;i<dateList.size();i++){
			if(counter%2==0){
			// Extract Data
				Map<String,List<List<Object>>> data=DataExtractor.getReportData(dateList.get(i-1), dateList.get(i));
				StringBuilder exportFilename =new StringBuilder(PropertiesUtil.getProperty("excelPath")).append("_");
				exportFilename.append(DateUtil.getDateToString(dateList.get(i-1), PropertiesUtil.getProperty("excelFileNameFormat"))).append("_")
					.append(DateUtil.getDateToString(dateList.get(i), PropertiesUtil.getProperty("excelFileNameFormat")))
					.append(PropertiesUtil.getProperty("excelFileExtn"));
				//System.out.println(exportFilename.toString());
				// Generate Report
				ReportExcel.writeExcel(data,exportFilename.toString());
			}
			counter++;
		}	
	}
}