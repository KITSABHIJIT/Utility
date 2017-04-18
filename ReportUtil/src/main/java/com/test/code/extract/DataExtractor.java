package com.test.code.extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.code.util.ConnectionUtil;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class DataExtractor {

	public static List<List<Object>> getData(String query){
		int recordCount=0;
		List<List<Object>> data = new ArrayList<List<Object>>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				) {
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			List<Object> headers = new ArrayList<Object>();
			for (int i = 1; i <= columnCount; i++ ) {
				headers.add(rsmd.getColumnName(i).replaceAll("_", " "));
			}
			data.add(headers);
			while (rs.next()) {
				List<Object> rowData = new ArrayList<Object>();
				for (int i = 1; i <= columnCount; i++ ) {
					rowData.add((StringUtil.isBlankOrEmpty(rs.getObject(i)))?"":rs.getObject(i));
				}
				data.add(rowData);
				recordCount++;
			}
			System.out.println("Total number of records fetched: "+recordCount);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return data;
	}
	
	public static Map<String,List<List<Object>>> getReportData(Date startDate, Date endDate){
		Map<String,List<List<Object>>> data=new HashMap<String, List<List<Object>>>();
		String reports=PropertiesUtil.getProperty("REPORTS");
		String[] reportList =reports.split(PropertiesUtil.getProperty("delimeter"));
		for(int i=0;i<reportList.length;i++){
			String query=PropertiesUtil.getProperty(reportList[i]);
			query=query.replaceAll(PropertiesUtil.getProperty("STARTDATE"), DateUtil.getDateToString(startDate,"yyyy-MM-dd"));
			query=query.replaceAll(PropertiesUtil.getProperty("ENDDATE"), DateUtil.getDateToString(endDate,"yyyy-MM-dd"));
			data.put(reportList[i].replaceAll("_", " "), getData(query));
		}
		return data;
	}
	
}
