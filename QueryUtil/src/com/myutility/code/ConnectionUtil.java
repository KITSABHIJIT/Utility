package com.myutility.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCDriver;

public class ConnectionUtil {
	public static final String RAINBOW="rainbow";
	public static final String SUNRISE="sunrise";
	public static final String COSMOS="cosmos";
	public static final String DAYBREAK="daybreak";
	public static final String SUNBEAM="sunbeam";
	public static final String EODS="eods";
	public static final String FSA="fsa";
	public static final String POLAR="polar";

	public static Connection getAS400Connection(String serverName,String library) {            
		System.out.println("Connect to Database Called");
		System.out.println("LIBRARY ACCESSED : " + library);

		AS400 server;
		String [] connectionArray; 
		Connection con=null;
		if(RAINBOW.equalsIgnoreCase(serverName)){
			connectionArray=PropertiesUtil.getProperty(RAINBOW).split("[|]");
			server = new AS400(connectionArray[0],connectionArray[1],connectionArray[2]);
		}else if(SUNRISE.equalsIgnoreCase(serverName)){
			connectionArray=PropertiesUtil.getProperty(SUNRISE).split("[|]");
			server = new AS400(connectionArray[0],connectionArray[1],connectionArray[2]);
		}else if(COSMOS.equalsIgnoreCase(serverName)){
			connectionArray=PropertiesUtil.getProperty(COSMOS).split("[|]");
			server = new AS400(connectionArray[0],connectionArray[1],connectionArray[2]);
		}else if(DAYBREAK.equalsIgnoreCase(serverName)){
			connectionArray=PropertiesUtil.getProperty(DAYBREAK).split("[|]");
			server = new AS400(connectionArray[0],connectionArray[1],connectionArray[2]);
		}else if(SUNBEAM.equalsIgnoreCase(serverName)){
			connectionArray=PropertiesUtil.getProperty(SUNBEAM).split("[|]");
			server = new AS400(connectionArray[0],connectionArray[1],connectionArray[2]);
		}else{
			server=null;
		}
		if(null!=server){
			boolean AS400Local;
			AS400Local = server.isLocal();
			Properties prop = new Properties();
			prop.put("naming", "system");
			prop.put("errors", "full");
			if(StringUtil.isBlankOrEmpty(library)){
				prop.put("libraries", "*LIBL");
			}else{
				prop.put("libraries", library);
			}
			System.out.println("Connect to Database Called after properties AS400Local "+AS400Local);
			try {
				server.connectService(AS400.DATABASE);
				System.out.println("DATABASE is connected to server ");
				AS400JDBCDriver d = new AS400JDBCDriver();
				con = d.connect(server, prop, null);
				con.setAutoCommit(true);
			}catch (Exception e){
				System.out.println("unable to connect to database."+e);
			}
		}else{
			System.out.println("Unknown Server...");
		}
		return con;
	}

	public static Connection getJDBCConnection(String serverName) {            
		System.out.println("Connect to Database Called: "+serverName);
		String [] connectionArray; 
		Connection con=null;
		try {
			if(EODS.equalsIgnoreCase(serverName)){
				connectionArray=PropertiesUtil.getProperty(EODS).split("[|]");
				con =  DriverManager.getConnection(connectionArray[0],connectionArray[1],connectionArray[2]);
			}else if(FSA.equalsIgnoreCase(serverName)){
				connectionArray=PropertiesUtil.getProperty(FSA).split("[|]");
				con =  DriverManager.getConnection(connectionArray[0],connectionArray[1],connectionArray[2]);
			}else if(POLAR.equalsIgnoreCase(serverName)){
				connectionArray=PropertiesUtil.getProperty(FSA).split("[|]");
				con =  DriverManager.getConnection(connectionArray[0],connectionArray[1],connectionArray[2]);
			}else{
				System.out.println("Unknown Server...");
			}
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if(null!=con){
				System.out.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
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
