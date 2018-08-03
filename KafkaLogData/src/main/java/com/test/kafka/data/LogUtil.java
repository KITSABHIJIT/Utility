package com.test.kafka.data;

import java.util.List;

import com.staples.kafka.log.pojo.LogFile;
import com.staples.kafka.log.util.ConfigUtil;
import com.staples.kafka.log.util.PropertiesUtil;
import com.staples.kafka.log.util.QueryUtil;

public class LogUtil {
	public static void main(String ...strings ) {
		PropertiesUtil.loadProperties("C:\\Eclipse Workspaces\\Utility_Workspace\\KafkaLogData\\config\\application.properties");
		ConfigUtil configUtil=new ConfigUtil();
		
		//List<LogFile> data = configUtil.getLogFileList();
		//QueryUtil.runInsertQuery(data);
		
		configUtil.createXML(QueryUtil.runSelectQuery());
		
	}
}
