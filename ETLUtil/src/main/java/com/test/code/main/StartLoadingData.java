package com.test.code.main;

import java.util.List;
import java.util.Map;

import com.test.code.extract.DataExtractor;
import com.test.code.load.DataLoader;
import com.test.code.pojo.Expense;
import com.test.code.pojo.Merchant;
import com.test.code.transform.DataTransformer;
import com.test.code.util.PropertiesUtil;

public class StartLoadingData {

	public static void main (String ...strings){
		//Expense Load
		// Extract Data
		Map<String,List<String>> rawData =DataExtractor.extractDataWithFileName(PropertiesUtil.getProperty("sourceDirPath"));
		// Transform Data
		List<Expense> expList=DataTransformer.transformDataUsingDelimeter(rawData, PropertiesUtil.getProperty("delimeter"));
		// Load Data
		DataLoader.loadExpenseData(expList);

		//Merchant Load
		// Extract Data
		Map<String,List<String>> rawDataMerchant =DataExtractor.extractDataWithFileName(PropertiesUtil.getProperty("sourceDirPathMerchant"));
		// Transform Data
		List<Merchant> merchantList=DataTransformer.transformDataUsingDelimeterMerchant(rawDataMerchant, PropertiesUtil.getProperty("delimeter"));
		// Load Data
		DataLoader.loadMerchantData(merchantList);

		// Extract Data
		//List<String> rawData =DataExtractor.extractData(PropertiesUtil.getProperty("sourceDirPath"));
		// Load Data
		//DataLoader.loadData(rawData);
	}
}