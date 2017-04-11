package com.test.code.main;

import java.util.List;
import java.util.Map;

import com.test.code.extract.DataExtractor;
import com.test.code.load.DataLoader;
import com.test.code.pojo.Expense;
import com.test.code.transform.DataTransformer;
import com.test.code.util.PropertiesUtil;

public class StartLoadingData {
	
	public static void main (String ...strings){
		// Extract Data
		Map<String,List<String>> rawData =DataExtractor.extractDataWithFileName(PropertiesUtil.getProperty("sourceDirPath"));
		// Transform Data
		List<Expense> expList=DataTransformer.transformDataUsingDelimeter(rawData, PropertiesUtil.getProperty("delimeter"));
		// Load Data
		DataLoader.loadExpenseData(expList);
		
		// Extract Data
		//List<String> rawData =DataExtractor.extractData(PropertiesUtil.getProperty("sourceDirPath"));
		// Load Data
		//DataLoader.loadData(rawData);
	}
}