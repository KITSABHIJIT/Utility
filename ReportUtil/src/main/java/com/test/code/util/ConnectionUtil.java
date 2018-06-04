package com.test.code.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {

	public static Connection getConnection() {            
		//System.out.println("Connect to Database Called");
		String [] connectionArray; 
		Connection con=null;
		connectionArray=PropertiesUtil.getProperty("MYSQL").split(PropertiesUtil.getProperty("delimeter"));
		if(null!=connectionArray){
			try {

				Class.forName("com.mysql.jdbc.Driver");

			} catch (ClassNotFoundException e) {

				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
			}
			//System.out.println("MySQL JDBC Driver Registered!");
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql:"+connectionArray[0], connectionArray[1],connectionArray[2]);
			} catch (SQLException e) {

				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
			}
			if (con != null) {
				//System.out.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}
		}else{
			System.out.println("Unknown Server...");
		}
		return con;
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if (null != rs) {
				rs.close();
				System.out.println("ResultSet closed Sucesfully");
			}
		} catch (Exception e) {
			System.out.println("Could not close ResultSet object");
			e.printStackTrace();
		}
	}
	public static void closeStatement(Statement stmt) {
		try {
			if (null != stmt) {
				stmt.close();
				System.out.println("Statement closed Sucesfully");
			}
		} catch (Exception e) {
			System.out.println("Could not close Statement object");
			e.printStackTrace();
		}
	}
	public static void closeConnection(Connection con) {
		try {
			if (null != con) {
				con.close();
				System.out.println("Connection closed Sucesfully");
			}
		} catch (Exception e) {
			System.out.println("Could not close Connection object");
			e.printStackTrace();
		}
	}
}
