package com.myutility.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class QueryUtil {
	public static final String HEADER="header";
	public static final String FOOTER="footer";
	public static final String ROW_START="rowStart";
	public static final String ROW_START_HEADER="rowStartHeader";
	public static final String ROW_START_BG_COLOR="rowStartBgColor";
	public static final String ROW_END="rowEnd";
	public static final String COLUMN_START="columnStart";
	public static final String COLUMN_END="columnEnd";
	public static final String COLUMN_CENTRE_START="centreStart";
	public static final String COLUMN_CENTRE_END="centreEnd";
	public static final String QUERY_OUTPUT="output.html";


	public static void main(String ...strings){
		String query="select * from sfpdflog WHERE PDFCRTDT =20150929";
		runSelectQuery(query,"cosmos","sf027slib");
	}


	public static void runSelectQuery(final String Query,final String server,final String library){
		Connection con=ConnectionUtil.getConnection(server,library);
		ResultSet rs=null;
		StringBuffer buffer=new StringBuffer(PropertiesUtil.getProperty(HEADER));
		PreparedStatement stmt=null;
		int bgColorCounter=1;
		Map < String, Object[] > queryData = 
				new TreeMap < String, Object[] >();
				try {
					stmt=con.prepareStatement(Query);
					rs = stmt.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					buffer.append(PropertiesUtil.getProperty(HEADER));
					// The column count starts from 1
					buffer.append(PropertiesUtil.getProperty(ROW_START_HEADER));
					Object[] columnHeader = new Object[columnCount];
					for (int i = 1; i <= columnCount; i++ ) {
						buffer.append(PropertiesUtil.getProperty(COLUMN_START))
						.append(PropertiesUtil.getProperty(COLUMN_CENTRE_START))
						.append(rsmd.getColumnName(i))
						.append(PropertiesUtil.getProperty(COLUMN_CENTRE_END)).
						append(PropertiesUtil.getProperty(COLUMN_END));
						columnHeader[i-1]=rsmd.getColumnName(i);
					}
					queryData.put( "1", columnHeader);
					buffer.append(PropertiesUtil.getProperty(ROW_END));
					while (rs.next()) {
						if(bgColorCounter%2==0){
							buffer.append(PropertiesUtil.getProperty(ROW_START_BG_COLOR));
						}else{
							buffer.append(PropertiesUtil.getProperty(ROW_START));
						}
						Object[] columnData = new Object[columnCount];
						for (int i = 1; i <= columnCount; i++ ) {
							buffer.append(PropertiesUtil.getProperty(COLUMN_START))
							.append(rs.getString(i).trim()).
							append(PropertiesUtil.getProperty(COLUMN_END));
							columnData[i-1]=rs.getString(i).trim();
						}
						queryData.put( String.valueOf(bgColorCounter+1), columnData);
						buffer.append(PropertiesUtil.getProperty(ROW_END));
						bgColorCounter++;
					}
					buffer.append(PropertiesUtil.getProperty(FOOTER));
					FileUtil.writeToFile(buffer.toString(), QUERY_OUTPUT);
					CreateExcel.createExcel(queryData, "Query Output");
				} catch (SQLException e) {
					System.out.println("Error while executing Query...");
					e.printStackTrace();
				}finally {
					ConnectionUtil.closeResultSet(rs);
					ConnectionUtil.closeStatement(stmt);
					ConnectionUtil.closeConnection(con);
				}
	}

}
