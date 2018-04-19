package com.myutility.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TransferDataUtil {
	
	public static void transferData(final String Query,final String sourceServer,final String sourceLibrary
			,final String destServer,final String destLibrary,final String destTable){
		Connection sourceCon=ConnectionUtil.getConnection(sourceServer,sourceLibrary);
		Connection destCon=ConnectionUtil.getConnection(destServer,destLibrary);
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
					insertStmt.setString(i, (StringUtil.isBlankOrEmpty(rs.getString(i))?"":rs.getString(i).trim()));
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
		String query="SELECT * FROM FCT01LIB.ERR1";
		String sourceLibrary=null;
		String sourceServer="moonbeam";
		String destLibrary="FC010Dlib";
		String destServer="daybreak";
		String destTableName="E810HST";
		TransferDataUtil.transferData(query, sourceServer, sourceLibrary,destServer,destLibrary,destTableName);
		
	}
	
	

}
