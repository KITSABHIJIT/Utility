package com.myutility.code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
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
	public static final String QUERY_OUTPUT_HTML="output.html";
	public static final String QUERY_OUTPUT_EXCEL="output.xlsx";
	public static final String QUERY_OUTPUT_CSV="output.csv";

	public static void runSelectQuery(final String Query,final String server,final String library
			,final String outputFormat,final boolean sendEmail){
		System.out.println("Query: "+Query);
		System.out.println("Server: "+server);
		System.out.println("Library: "+library);
		System.out.println("OutputFormat: 1 for HTML, 2 for Excel and 3 or default is CSV\n Your selection is: "+outputFormat);
		Connection con=ConnectionUtil.getAS400Connection(server,library);
		ResultSet rs=null;
		StringBuffer buffer=new StringBuffer();
		PreparedStatement stmt=null;
		int bgColorCounter=1;
		Map < String, Object[] > queryData = 
				new TreeMap < String, Object[] >();
				try {
					stmt=con.prepareStatement(Query);
					rs = stmt.executeQuery();
					System.out.println("Query execution completed: "+Query);
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					System.out.println("Number of Column in the output: "+columnCount);
					// HTML Format
					if("1".equals(outputFormat)){
						buffer.append(PropertiesUtil.getProperty(HEADER));
						buffer.append(PropertiesUtil.getProperty(HEADER));
						// The column count starts from 1
						buffer.append(PropertiesUtil.getProperty(ROW_START_HEADER));
						for (int i = 1; i <= columnCount; i++ ) {
							buffer.append(PropertiesUtil.getProperty(COLUMN_START))
							.append(PropertiesUtil.getProperty(COLUMN_CENTRE_START))
							.append(rsmd.getColumnName(i))
							.append(PropertiesUtil.getProperty(COLUMN_CENTRE_END)).
							append(PropertiesUtil.getProperty(COLUMN_END));
						}
						buffer.append(PropertiesUtil.getProperty(ROW_END));
						while (rs.next()) {
							if(bgColorCounter%2==0){
								buffer.append(PropertiesUtil.getProperty(ROW_START_BG_COLOR));
							}else{
								buffer.append(PropertiesUtil.getProperty(ROW_START));
							}
							for (int i = 1; i <= columnCount; i++ ) {
								buffer.append(PropertiesUtil.getProperty(COLUMN_START))
								.append((StringUtil.isBlankOrEmpty(rs.getString(i)))?"":rs.getString(i).trim()).
								append(PropertiesUtil.getProperty(COLUMN_END));
							}
							buffer.append(PropertiesUtil.getProperty(ROW_END));
							bgColorCounter++;
						}
						buffer.append(PropertiesUtil.getProperty(FOOTER));
						System.out.println("Creating HTML output file: "+QUERY_OUTPUT_HTML);
						FileUtil.writeToFile(buffer.toString(), QUERY_OUTPUT_HTML);
						if(sendEmail){
							EmailUtil.sendEmail(true,QUERY_OUTPUT_HTML);	
						}
					}
					// Excel Format
					else if("2".equals(outputFormat)){
						Object[] columnHeader = new Object[columnCount];
						for (int i = 1; i <= columnCount; i++ ) {
							columnHeader[i-1]=rsmd.getColumnName(i);
						}
						queryData.put( "1", columnHeader);
						while (rs.next()) {
							Object[] columnData = new Object[columnCount];
							for (int i = 1; i <= columnCount; i++ ) {
								columnData[i-1]=(StringUtil.isBlankOrEmpty(rs.getString(i)))?"":rs.getString(i).trim();
							}
							queryData.put( String.valueOf(bgColorCounter+1), columnData);
							bgColorCounter++;
						}
						System.out.println("Creating Excel output file: "+QUERY_OUTPUT_EXCEL);
						CreateExcel.createExcel(queryData, QUERY_OUTPUT_EXCEL);
						if(sendEmail){
							EmailUtil.sendEmail(true,QUERY_OUTPUT_EXCEL);
						}
					}
					// CSV Format
					else{
						String dataHeaders = "\"" + rsmd.getColumnName(1) + "\"" ; 
						for (int i = 2 ; i < columnCount + 1 ; i ++ ) { 
							dataHeaders += ",\"" + rsmd.getColumnName(i) + "\"" ;
						}
						buffer.append(dataHeaders) ;
						buffer.append(System.getProperty("line.separator"));
						while (rs.next()) {
							String row = "\"" + rs.getString(1) + "\""  ; 
							for (int i = 2 ; i < columnCount + 1 ; i ++ ) {
								row += ",\"" + ((StringUtil.isBlankOrEmpty(rs.getString(i)))?"":rs.getString(i).trim()) + "\"" ;
							}
							buffer.append(row) ;
							buffer.append(System.getProperty("line.separator"));
						}
						System.out.println("Creating CSV output file: "+QUERY_OUTPUT_CSV);
						FileUtil.writeToFile(buffer.toString(), QUERY_OUTPUT_CSV);
						if(sendEmail){
							EmailUtil.sendEmail(true,QUERY_OUTPUT_CSV);
						}
					}

				} catch (SQLException e) {
					System.out.println("Error while executing Query...");
					e.printStackTrace();
				}finally {
					ConnectionUtil.closeResultSet(rs);
					ConnectionUtil.closeStatement(stmt);
					ConnectionUtil.closeConnection(con);
				}
	}

	public static void runCountQuery(final String query,final String server,final boolean inputRequired){
		System.out.println("Server: "+server);
		Connection con=ConnectionUtil.getJDBCConnection(server);
		int count=0;
		int threshold=Integer.parseInt(PropertiesUtil.getProperty("threshold"));
		ResultSet rs=null;
		StringBuffer dataBuffer=new StringBuffer();
		PreparedStatement stmt=null;
		try{
			if(inputRequired){
				List<String> inputData=FileUtil.readFromFile(PropertiesUtil.inputDataPath);
				int loopCounter=0;
				for(String data:inputData){
					if(loopCounter==threshold){
						String tempQuery=query.replaceAll("<DATA>", dataBuffer.toString());
						System.out.println("Query Executed: "+tempQuery);
						stmt=con.prepareStatement(tempQuery);
						rs = stmt.executeQuery();
						if (rs.next()) {
							int tempCount=rs.getInt(1);
							System.out.println("Query Count from "+server+" with "+loopCounter+" input is: "+tempCount);
							count=count+tempCount;
						}
						loopCounter=0;
						//clear the Stringbuffer content
						dataBuffer.delete(0, dataBuffer.length());
					}
					if(dataBuffer.length()<=0){
						dataBuffer.append("'");
						dataBuffer.append(data);
						dataBuffer.append("'");
					}else{
						dataBuffer.append(",'");
						dataBuffer.append(data);
						dataBuffer.append("'");
					}
					loopCounter++;
				}
				if(loopCounter<threshold){
					String tempQuery=query.replaceAll("<DATA>", dataBuffer.toString());
					System.out.println("Query Executed: "+tempQuery);
					stmt=con.prepareStatement(tempQuery);
					rs = stmt.executeQuery();
					if (rs.next()) {
						int tempCount=rs.getInt(1);
						System.out.println("Query Count from "+server+" with "+loopCounter+" input is: "+tempCount);
						count=count+tempCount;
					}
					loopCounter=0;
				}
			}else{
				stmt=con.prepareStatement(query);
				rs = stmt.executeQuery();
				if (rs.next()) {
					int tempCount=rs.getInt(1);
					System.out.println("Query Executed: "+query);
					count=tempCount;
				}
			}
			System.out.println("Total Query Count from "+server+" is: "+count);


		} catch (SQLException e) {
			System.out.println("Error while executing Query...");
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeResultSet(rs);
			ConnectionUtil.closeStatement(stmt);
			ConnectionUtil.closeConnection(con);
		}
	}


	public static void runDMLQuery(final String queryDir,final String server,boolean dropExisting,String objectType){
		System.out.println("Server: "+server);
		Connection con=ConnectionUtil.getJDBCConnection(server);
		PreparedStatement stmt=null;
		boolean isError=false;
		try{
			con.setAutoCommit(false);
			for(String fileName:FileUtil.getListOfFiles(queryDir, true)){
				if(!isError){
					String tableName=StringUtil.getFileNameWithoutExtn(fileName);
					boolean tableExists=false;
					DatabaseMetaData meta = con.getMetaData();
					ResultSet res = meta.getTables(null, null, tableName,new String[] {objectType});
					if(res.next()) {
						tableExists=true;
					}
					if(tableExists && dropExisting){
						String dropSQL="DROP "+objectType+" "+tableName;
						try{
							stmt=con.prepareStatement(dropSQL);
							stmt.executeUpdate();
							System.out.println("Success: "+dropSQL);
							tableExists=false;
						} catch (SQLException e) {
							System.out.println("Error: "+dropSQL);
							con.rollback();
							isError=true;
							e.printStackTrace();
						}finally {
							ConnectionUtil.closeStatement(stmt);
						}
					}
					if(!tableExists){
						for(String query:FileUtil.readFromFile(fileName)){
							if(!isError){
								try{
									stmt=con.prepareStatement(query);
									stmt.executeUpdate();
									System.out.println("Success: "+query);
								} catch (SQLException e) {
									System.out.println("Error: "+query);
									con.rollback();
									isError=true;
									e.printStackTrace();
								}finally {
									ConnectionUtil.closeStatement(stmt);
								}
							}else{
								break;
							}
						}
					}else{
						System.out.println("***********"+tableName+" already existed***********");
					}

				}else{
					break;
				}
			}
			if(!isError){
				con.commit();
			}else{
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeConnection(con);

		}
	}

}
