package com.test.code.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {

	public static Connection getConnection() {            
		System.out.println("Connect to Database Called");
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
			System.out.println("MySQL JDBC Driver Registered!");
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql:"+connectionArray[0], connectionArray[1],connectionArray[2]);
			} catch (SQLException e) {

				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
			}
			if (con != null) {
				System.out.println("You made it, take control your database now!");
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
	
	public static void backupdbtosql() {
 		try {
 			String [] connectionArray; 
 			connectionArray=PropertiesUtil.getProperty("MYSQL").split(PropertiesUtil.getProperty("delimeter"));
 			if(null!=connectionArray){
 				String dbUser = connectionArray[1];
 				String dbPass = connectionArray[2];
 				String folderPath = PropertiesUtil.getProperty("databaseBackup");
 				File f1 = new File(folderPath);
 				if(!f1.exists()){
 					f1.mkdir();
 				}
 				String savePath = f1+"/backup"+DateUtil.getSomeDateString(null, null, "yyyyMMddhhmmss")+".sql";

 				ProcessBuilder pb = new ProcessBuilder(
 				        new String[]{
 				            "/usr/local/mysql/bin/mysqldump", 
 				            "-u" + dbUser,
 				            "-p" + dbPass,
 				            PropertiesUtil.getProperty("MYSQLDB"),
 				            "-r", savePath
 				        }
 				);
 				pb.redirectErrorStream(true);
 				try {
 				    Process p = pb.start();
 				    InputStream is = p.getInputStream();
 				    int in = -1;
 				    while ((in = is.read()) != -1) {
 				        System.out.print((char)in);
 				    }
 				    int exitWith = p.exitValue();
 				    System.out.println("\nExited with " + exitWith);
 				} catch (IOException exp) {
 				    exp.printStackTrace();
 				}
 			}
 		} catch (Exception ex) {
 			System.out.println("Could not take database backup");
 			ex.printStackTrace();
 		}
 	}
	
	public static void backupdbtosqlWindows() {
		try {
			String [] connectionArray; 
			connectionArray=PropertiesUtil.getProperty("MYSQL").split(PropertiesUtil.getProperty("delimeter"));
			if(null!=connectionArray){
				String dbUser = connectionArray[1];
				String dbPass = connectionArray[2];
				String folderPath = PropertiesUtil.getProperty("databaseBackup");
				File f1 = new File(folderPath);
				if(!f1.exists()){
					f1.mkdir();
				}
				String savePath = f1+System.getProperty("file.separator")+"backup"+DateUtil.getSomeDateString(null, null, "yyyyMMddhhmmss")+".sql";
				
				ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "C:\\\"Program Files\"\\MySQL\\\"MySQL Server 8.0\"\\bin\\mysqldump -u "+dbUser+" -p "+dbPass+" "+PropertiesUtil.getProperty("MYSQLDB")+" > "
				        + savePath);
				try {
				    Process exec = pb.start();
				    int retCode = exec.waitFor();
				    if (retCode != 0) { //4
				        // something went wrong
				        InputStream errorStream = exec.getErrorStream();
				        byte[] buffer = new byte[errorStream.available()];
				        errorStream.read(buffer);

				        System.out.println(new String(buffer));
				    }
				} catch (IOException exp) {
				    exp.printStackTrace();
				}
			}
		} catch (Exception ex) {
			System.out.println("Could not take database backup");
			ex.printStackTrace();
		}
	}
	
	public static void cleanupData() {
		try (Connection con =getConnection();
				PreparedStatement statement = con.prepareStatement("delete from EARNING");
				PreparedStatement statement1 = con.prepareStatement("delete from EXPENSE");
				PreparedStatement statement2 = con.prepareStatement("delete from EXPENSE_CATEGORY");
				PreparedStatement statement3 = con.prepareStatement("delete from MERCHANT");
				PreparedStatement statement4 = con.prepareStatement("delete from MERCHANT_MAPPING");
				PreparedStatement statement5 = con.prepareStatement("delete from PAYMODE");
				) {
			statement.executeUpdate();
			statement1.executeUpdate();
			statement2.executeUpdate();
			statement3.executeUpdate();
			statement4.executeUpdate();
			statement5.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
