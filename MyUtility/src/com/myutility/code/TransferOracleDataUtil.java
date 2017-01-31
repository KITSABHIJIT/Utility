package com.myutility.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TransferOracleDataUtil {
	
	public static void transferData(final String Query,final String sourceServer
			,final String destServer,final String destTable){
		Connection sourceCon=ConnectionUtil.getOracleConnection(sourceServer);
		Connection destCon=ConnectionUtil.getOracleConnection(destServer);
		ResultSet rs=null;
		PreparedStatement stmt=null;
		PreparedStatement insertStmt=null;
		try {
			stmt=sourceCon.prepareStatement(Query);
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String insertColumns=null;
			String insertValues=null;
			// The column count starts from 1
			for (int i = 1; i <= columnCount; i++ ) {
				insertColumns=(StringUtil.isBlankOrEmpty(insertColumns))?rsmd.getColumnName(i):insertColumns+","+rsmd.getColumnName(i);
				insertValues=(StringUtil.isBlankOrEmpty(insertValues))?"?":insertValues+",?";
			}
			String insertQuery="INSERT INTO "+destTable+" ( "+insertColumns+" ) VALUES ( "+insertValues+" )";
			System.out.println("Insert Query: "+insertQuery);
			insertStmt=destCon.prepareStatement(insertQuery);
			destCon.setAutoCommit(false);

			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++ ) {
					//insertStmt.setString(i, (StringUtil.isBlankOrEmpty(rs.getString(i))?"":rs.getString(i).trim()));
					insertStmt.setString(i, rs.getString(i));
				}
				insertStmt.addBatch();
			}
			int[] result=insertStmt.executeBatch();
			System.out.println(result.length+" record inserted succussfully");
			destCon.commit();
		} catch (SQLException e) {
			System.out.println("Error while executing Query...");
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeResultSet(rs);
			ConnectionUtil.closeStatement(stmt);
			ConnectionUtil.closeConnection(sourceCon);
			ConnectionUtil.closeConnection(destCon);
		}
	}
	
	public static void main(String[] args) {
		String query="select VENDOR_NUM "
					+",VITEM_FIND_NUM "
					+",EFFECTIVE_DATE "
					+",VITEM_CTRL_NUM "
					+",VITEM_PRINT_NUM "
					+",TIME_STAMP "
					+",ROW_STAT_CD from FSA_STG.QUILL_VENDOR_ITEM_NAME";
		//String query="select * from FSA_STG.QUILL_VENDOR";
		String sourceServer="sourceOracle";
		String destServer="destOracle";
		String destTableName="QUILL_VENDOR";
		TransferOracleDataUtil.transferData(query, sourceServer, destServer,destTableName);
		
	}
	
	

}
