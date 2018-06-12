package com.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtils {

	private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "abhijit";
	
	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/expdb?useSSL=false";
	
	private static Connection connection;
	
	public static Connection getConnection() {
		
		try {
			
			// loading the driver
			Class.forName(DRIVERNAME);
			
			// getting the connection
			connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return connection;
	}
}
