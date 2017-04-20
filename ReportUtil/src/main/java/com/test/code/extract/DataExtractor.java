package com.test.code.extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.general.DefaultPieDataset;

import com.test.code.pojo.PieChartData;
import com.test.code.pojo.ReportData;
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

	public static DefaultPieDataset getPeiGraphData(String query){
		DefaultPieDataset data = new DefaultPieDataset();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				data.setValue(rs.getString(1),rs.getDouble(2));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return data;
	}

	public static String updateQueryWithDates(String query,Date startDate, Date endDate){
		query=query.replaceAll(PropertiesUtil.getProperty("STARTDATE"), DateUtil.getDateToString(startDate,"yyyy-MM-dd"));
		query=query.replaceAll(PropertiesUtil.getProperty("ENDDATE"), DateUtil.getDateToString(endDate,"yyyy-MM-dd"));
		return query;
	}
	public static Map<String,ReportData> getReportData(Date startDate, Date endDate){
		Map<String,ReportData> data=new LinkedHashMap<String, ReportData>();
		String reports=PropertiesUtil.getProperty("REPORTS");
		String[] sheetList =reports.split(PropertiesUtil.getProperty("delimeter"));
		String sheetname="";
		for(String sheet:sheetList){
			ReportData reportData = new ReportData();
			List<PieChartData> pieChartData=new ArrayList<PieChartData>();
			String[] figureList =sheet.split(PropertiesUtil.getProperty("figureDelimeter"));
			String query=PropertiesUtil.getProperty(figureList[0]);
			sheetname=figureList[0];
			query=updateQueryWithDates(query,startDate,endDate);
			reportData.setReportData(getData(query));
			if(figureList.length>1){
				String[] chartList =figureList[1].split(PropertiesUtil.getProperty("commaDelimeter"));
				for(String chart:chartList){
					if(chart.startsWith("PIE")){
						PieChartData peiChart =new PieChartData();
						String chartQuery=updateQueryWithDates(PropertiesUtil.getProperty(chart),startDate,endDate);
						peiChart.setDataset(getPeiGraphData(chartQuery));
						String [] chartConfig=PropertiesUtil.getProperty("CONFIG_"+chart).split(PropertiesUtil.getProperty("delimeter"));
						peiChart.setTitle(chartConfig[0]);
						peiChart.setLegend(Boolean.parseBoolean(chartConfig[1]));
						peiChart.setTooltips(Boolean.parseBoolean(chartConfig[2]));
						peiChart.setUrls(Boolean.parseBoolean(chartConfig[3]));
						peiChart.setLength(Integer.parseInt(chartConfig[4]));
						peiChart.setHeight(Integer.parseInt(chartConfig[5]));
						peiChart.setRowPosition(Integer.parseInt(chartConfig[6]));
						peiChart.setColumnPosition(Integer.parseInt(chartConfig[7]));
						pieChartData.add(peiChart);
					}else if(PropertiesUtil.getProperty("chart").startsWith("BAR")){

					}
				}
				reportData.setPieData(pieChartData);
			}
			data.put(sheetname.replaceAll("_", " "), reportData);
		}
		return data;
	}

}
