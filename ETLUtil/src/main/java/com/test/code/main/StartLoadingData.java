package com.test.code.main;

import java.util.List;
import java.util.Map;

import com.test.code.extract.DataExtractor;
import com.test.code.extract.DataTransformer;
import com.test.code.load.DataLoader;
import com.test.code.pojo.Category;
import com.test.code.pojo.Expense;
import com.test.code.pojo.Merchant;
import com.test.code.pojo.PayMode;
import com.test.code.transform.AmexTransformer;
import com.test.code.transform.BJsTransformer;
import com.test.code.transform.BOACreditTransformer;
import com.test.code.transform.BOADebitTransformer;
import com.test.code.transform.BestBuyTransformer;
import com.test.code.transform.ChaseTransformer;
import com.test.code.transform.DCUDebitTransformer;
import com.test.code.transform.DiscoverTransformer;
import com.test.code.transform.JCPenneyTransformer;
import com.test.code.transform.KholsTransformer;
import com.test.code.transform.MacysTransformer;
import com.test.code.transform.TjMaxTransformer;
import com.test.code.transform.ZalesTransformer;
import com.test.code.util.PropertiesUtil;

public class StartLoadingData {

	public static void main (String ...strings){
		//Expense Load
		// Extract Data
		Map<String,List<String>> rawData =DataExtractor.extractDataWithFileName(PropertiesUtil.getProperty("sourceDirPath"));
		// Transform Data
		List<Expense> expList=DataTransformer.transformDataUsingDelimeter(rawData, PropertiesUtil.getProperty("delimeter"));
		// Load Data
		AmexTransformer.processData(expList);
		BestBuyTransformer.processData(expList);
		BJsTransformer.processData(expList);
		BOACreditTransformer.processData(expList);
		BOADebitTransformer.processData(expList);
		DCUDebitTransformer.processData(expList);
		ChaseTransformer.processData(expList);
		DiscoverTransformer.processData(expList);
		JCPenneyTransformer.processData(expList);
		KholsTransformer.processData(expList);
		MacysTransformer.processData(expList);
		TjMaxTransformer.processData(expList);
		ZalesTransformer.processData(expList);
		DataLoader.loadExpenseData(expList);
		
		//Category Load
		// Extract Data
		Map<String,List<String>> rawDataCategory =DataExtractor.extractDataWithFileName(PropertiesUtil.getProperty("sourceDirPathCategory"));
		// Transform Data
		List<Category> categoryList=DataTransformer.transformDataUsingDelimeterCategory(rawDataCategory, PropertiesUtil.getProperty("delimeter"));
		// Load Data
		DataLoader.loadCategoryData(categoryList);
		
		//Merchant Load
		// Extract Data
		Map<String,List<String>> rawDataMerchant =DataExtractor.extractDataWithFileName(PropertiesUtil.getProperty("sourceDirPathMerchant"));
		// Transform Data
		List<Merchant> merchantList=DataTransformer.transformDataUsingDelimeterMerchant(rawDataMerchant, PropertiesUtil.getProperty("delimeter"));
		// Load Data
		DataLoader.loadMerchantMappingData(merchantList);
		DataLoader.loadNewMerchant();
		

		//PAYMODE Load
		// Extract Data
		Map<String,List<String>> rawDataPayMode =DataExtractor.extractDataWithFileName(PropertiesUtil.getProperty("sourceDirPathPayMode"));
		// Transform Data
		List<PayMode> payModeList=DataTransformer.transformDataUsingDelimeterPayMode(rawDataPayMode, PropertiesUtil.getProperty("delimeter"));
		// Load Data
		DataLoader.loadPayModeData(payModeList);


		// Extract Data
		//List<String> rawData =DataExtractor.extractData(PropertiesUtil.getProperty("sourceDirPath"));
		// Load Data
		//DataLoader.loadData(rawData);
	}
}