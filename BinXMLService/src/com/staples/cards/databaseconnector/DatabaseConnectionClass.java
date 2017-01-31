package com.staples.cards.databaseconnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCDriver;
import com.staples.cards.constants.CardProperties;
import com.staples.cards.logger.CardLogger;

public class DatabaseConnectionClass {

	/**
	 * This method establishes a connection to the AS400 database library as specified by the input parameter library  
	 * @param library	name of the AS400 database library to which connection is to be established
	 * @return			connection to the specified AS400 database library
	 */
	public static Connection connectToDatabase(String library) {            
		AS400 server;
		Connection con=null;
		server = new AS400(CardProperties.DATABASE_SERVER,
				CardProperties.DATABASE_USER, CardProperties.DATABASE_PWD);
		Properties prop = new Properties();
		prop.put("naming", "system");
		prop.put("errors", "full");
		prop.put("libraries", library);
		try {
			server.connectService(AS400.DATABASE);
			AS400JDBCDriver d = new AS400JDBCDriver();
			con = d.connect(server, prop, null);
			con.setAutoCommit(true);
			//}
		}catch (Exception e){
			CardLogger.error("unable to connect to database.",e);
		}
		return con;
}
	
	/**
	 * This method closes Connection and PreparedStatement objects
	 * @param con		Connection object	
	 * @param pStmt		PreparedStatement object
	 * @param resultSet	ResultSet object
	 */
	public static void closeConnection(Connection con, PreparedStatement pStmt,ResultSet resultSet) {

		try {
			if (null != resultSet) {
				resultSet.close();
			}
		} catch (SQLException e) {
			CardLogger.error("Could not close ResultSet object", e);
		}
		try {
			if (null != pStmt) {
				pStmt.close();
			}
		} catch (SQLException e) {
			CardLogger.error("Could not close PreparedStatement object", e);
		}

		try {
			if (null != con) {
				con.close();
			}
		} catch (SQLException e) {
			CardLogger.error("Could not close Connection object", e);
		}

	}
	
	
	
}
