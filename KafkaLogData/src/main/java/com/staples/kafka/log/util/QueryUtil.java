package com.staples.kafka.log.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.staples.kafka.log.pojo.LogFile;

public class QueryUtil {

	public static void runInsertQuery(final List<LogFile> data){

		try (
				Connection connection = ConnectionUtil.getMySQLConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO LOGDETAILS VALUES (?,?,?,?,?,?,?,?)");
				) {
			int recordCount= 0;

			for (LogFile entity : data) {
				statement.setString(1, entity.getApplicationID());
				statement.setString(2, entity.getJobName());
				statement.setString(3, entity.getLogLevel());
				statement.setString(4, entity.getLogfilePath());
				statement.setString(5, entity.getRolledOutLogfilePath());
				statement.setString(6, entity.getKafkaTopic());
				statement.setString(7, String.valueOf(entity.getIsAync()));
				String excludeData="";
				for(String excludeDataTemp:entity.getExcludedLog()) {
					if("".equals(excludeData)){
						excludeData=excludeDataTemp;
					}else {
						excludeData="||"+excludeDataTemp;
					}
				}
				statement.setString(8, excludeData);
				statement.addBatch();
				recordCount++;
				if (recordCount % 1000 == 0 || recordCount == data.size()) {
					statement.executeBatch(); // Execute every 1000 items.
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<LogFile> runSelectQuery(){
		List<LogFile> data=new ArrayList<LogFile>();
		ResultSet rs=null;
		try (
				Connection connection = ConnectionUtil.getMySQLConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM LOGDETAILS");
				) {
			rs = statement.executeQuery();
			while (rs.next()) {
				LogFile file=new LogFile();
				file.setApplicationID(rs.getString(1));
				file.setJobName(rs.getString(2));
				file.setLogLevel(rs.getString(3));
				file.setLogfilePath(rs.getString(4));
				file.setRolledOutLogfilePath(rs.getString(5));
				file.setKafkaTopic(rs.getString(6));
				file.setIsAync(Boolean.parseBoolean(rs.getString(7)));
				String excludeLogData=rs.getString(8);
				List<String> excludeLogList=null;
				if(!StringUtil.isBlankOrEmpty(excludeLogData)) {
					excludeLogList=new ArrayList<String>();
					for(String excludeLogTemp:excludeLogData.split("[||]")) {
						excludeLogList.add(excludeLogTemp);
					}
				}
				file.setExcludedLog(excludeLogList);
				data.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeResultSet(rs);
		}
		return data;
	}

}
