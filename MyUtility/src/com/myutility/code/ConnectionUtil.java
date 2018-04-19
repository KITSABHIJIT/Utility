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
	public static final String MOONBEAM="moonbeam";
	
	public static final String SOURCE_ORACLE="sourceOracle";
	public static final String DESTINATION_ORACLE="destOracle";
	
	public static Connection getConnection(String serverName,String library) {            
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
		}else if(MOONBEAM.equalsIgnoreCase(serverName)){
			connectionArray=PropertiesUtil.getProperty(MOONBEAM).split("[|]");
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

	public static Connection getOracleConnection(String serverName) {            
		System.out.println("Connect to Database Called");
		String [] connectionArray; 
		Connection con=null;
		if(SOURCE_ORACLE.equalsIgnoreCase(serverName)){
			connectionArray=PropertiesUtil.getProperty(SOURCE_ORACLE).split("[|]");
		}else if(DESTINATION_ORACLE.equalsIgnoreCase(serverName)){
			connectionArray=PropertiesUtil.getProperty(DESTINATION_ORACLE).split("[|]");
		}else{
			connectionArray=null;
		}
		if(null!=connectionArray){
			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");

			} catch (ClassNotFoundException e) {

				System.out.println("Where is your Oracle JDBC Driver?");
				e.printStackTrace();
			}
			System.out.println("Oracle JDBC Driver Registered!");
			try {
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@"+connectionArray[0], connectionArray[1],connectionArray[2]);
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
}
