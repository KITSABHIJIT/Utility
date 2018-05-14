package com.staples.jenkins.build;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCDriver;

public class ConnectionUtil {

	public static Connection getAS400Connection(String library) {            
		System.out.println("Connecting "+PropertiesUtil.getProperty("server")+"...");
		if(!StringUtil.isBlankOrEmpty(library))
			System.out.println("LIBRARY ACCESSED : " + library);

		AS400 server;
		Connection con=null;
		server = new AS400(PropertiesUtil.getProperty("server"),PropertiesUtil.getProperty("user"),PropertiesUtil.getProperty("password"));
		if(null!=server){
			Properties prop = new Properties();
			prop.put("naming", "system");
			prop.put("errors", "full");
			if(StringUtil.isBlankOrEmpty(library)){
				prop.put("libraries", "*LIBL");
			}else{
				prop.put("libraries", library);
			}
			try {
				server.connectService(AS400.DATABASE);
				System.out.println("Connected to :"+PropertiesUtil.getProperty("server"));
				AS400JDBCDriver d = new AS400JDBCDriver();
				con = d.connect(server, prop, null);
				con.setAutoCommit(true);
			}catch (Exception e){
				System.out.println("unable to connect "+PropertiesUtil.getProperty("server"));
				e.printStackTrace();
			}
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
	public static void closeStatement(CallableStatement stmt) {
		try {
			if (null != stmt) {
				stmt.close();
				//System.out.println("Statement closed Sucesfully");
			}
		} catch (Exception e) {
			System.out.println("Could not close Callable Statement object");
			e.printStackTrace();
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (null != stmt) {
				stmt.close();
				//System.out.println("Statement closed Sucesfully");
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

	public static int insertBuildData(BuildLog buildLog) {
		Connection con=ConnectionUtil.getAS400Connection(null);
		PreparedStatement stmt=null;
		int insertedRows=0;
		try {
			stmt=con.prepareStatement(PropertiesUtil.getProperty("insertBuildLog"));
			stmt.setString(1, buildLog.getBuildId());
			stmt.setString(2, buildLog.getBuildTag());
			stmt.setString(3, buildLog.getBuildUrl());
			stmt.setString(4, buildLog.getGitCommit());
			stmt.setString(5, buildLog.getGitBranch());
			stmt.setString(6, buildLog.getAs400JobName());
			stmt.setString(7, buildLog.getAs400IfsPath());
			insertedRows = stmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Error while executing Query..."+PropertiesUtil.getProperty("insertBuildLog"));
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeStatement(stmt);
			ConnectionUtil.closeConnection(con);
		}
		return insertedRows;
	}
	
	public static int updateCaRule(String jobName,String status) {
		Connection con=ConnectionUtil.getAS400Connection(null);
		PreparedStatement stmt=null;
		int insertedRows=0;
		try {
			stmt=con.prepareStatement(PropertiesUtil.getProperty("updateCaRule"));
			stmt.setString(1, status);
			stmt.setString(2, jobName);
			insertedRows = stmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Error while executing Query..."+PropertiesUtil.getProperty("updateCaRule"));
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeStatement(stmt);
			ConnectionUtil.closeConnection(con);
		}
		return insertedRows;
	}
	
	public static CaRule getCaRuleData(String jobName) {
		Connection con=ConnectionUtil.getAS400Connection(null);
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		CaRule caRule=new CaRule();
		try {
			stmt=con.prepareStatement(PropertiesUtil.getProperty("getCaRule"));
			stmt.setString(1, jobName);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				caRule.setProgramName(resultSet.getString(PropertiesUtil.getProperty("carule_program")));
				caRule.setMaxInstance(resultSet.getDouble(PropertiesUtil.getProperty("carule_job_instance")));
				caRule.setStatus(resultSet.getString(PropertiesUtil.getProperty("carule_job_status")));
				caRule.setJobName(resultSet.getString(PropertiesUtil.getProperty("carule_job_name")));
				caRule.setJobD(resultSet.getString(PropertiesUtil.getProperty("carule_jobd")));
				caRule.setControlProgram(resultSet.getString(PropertiesUtil.getProperty("carule_cltpgm")));
				caRule.setDelay(resultSet.getString(PropertiesUtil.getProperty("carule_delay")));
				caRule.setPriority(resultSet.getLong(PropertiesUtil.getProperty("carule_priority")));
			}
		}catch (SQLException e) {
			System.out.println("Error while executing Query..."+PropertiesUtil.getProperty("getCaRule"));
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeStatement(stmt);
			ConnectionUtil.closeConnection(con);
			ConnectionUtil.closeResultSet(resultSet);
		}
		return caRule;
	}

}
