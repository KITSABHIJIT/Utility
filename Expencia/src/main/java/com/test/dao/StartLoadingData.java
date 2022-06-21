package com.test.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.code.util.ConnectionUtil;
import com.test.code.util.PropertiesUtil;
import com.test.etl.extract.DataExtractor;
import com.test.etl.extract.DataTransformer;
import com.test.etl.load.DataLoader;
import com.test.etl.pojo.Category;
import com.test.etl.pojo.Earning;
import com.test.etl.pojo.Expense;
import com.test.etl.pojo.Merchant;
import com.test.etl.pojo.PayMode;
import com.test.etl.transform.AmazonStroreTransformer;
import com.test.etl.transform.AmexBlueCashTransformer;
import com.test.etl.transform.AmexCashMagnetTransformer;
import com.test.etl.transform.AmexTransformer;
import com.test.etl.transform.BJsTransformer;
import com.test.etl.transform.BOACreditTransformer;
import com.test.etl.transform.BOADebitTransformer;
import com.test.etl.transform.BOATravelCreditTransformer;
import com.test.etl.transform.BarclaysTransformer;
import com.test.etl.transform.BestBuyTransformer;
import com.test.etl.transform.CapitalOneTransformer;
import com.test.etl.transform.CashTransformer;
import com.test.etl.transform.ChaseSapphireTransformer;
import com.test.etl.transform.ChaseTransformer;
import com.test.etl.transform.CitiTransformer;
import com.test.etl.transform.DCUDebitTransformer;
import com.test.etl.transform.DiscoverTransformer;
import com.test.etl.transform.JCPenneyTransformer;
import com.test.etl.transform.KholsTransformer;
import com.test.etl.transform.MacysTransformer;
import com.test.etl.transform.TjMaxTransformer;
import com.test.etl.transform.WalmartTransformer;
import com.test.etl.transform.WellsFargoTransformer;
import com.test.etl.transform.ZalesTransformer;
@Service
public class StartLoadingData {

	public void loadData (){
		
		ConnectionUtil.backupdbtosql();
		ConnectionUtil.cleanupData();
		//Expense Load
		// Extract Data
		Map<String,List<String>> rawData =DataExtractor.extractDataWithFileName(PropertiesUtil.getProperty("sourceDirPath"));
		// Transform Data
		List<Expense> expList=DataTransformer.transformExpenseUsingDelimeter(rawData, PropertiesUtil.getProperty("delimeter"));
		// Transform Data
		List<Earning> earningList=DataTransformer.transformEarningUsingDelimeter(rawData, PropertiesUtil.getProperty("delimeter"));
		// Load Data
		AmexTransformer.processData(expList);
		AmazonStroreTransformer.processData(expList);
		BestBuyTransformer.processData(expList);
		BJsTransformer.processData(expList);
		BOACreditTransformer.processData(expList);
		BOADebitTransformer.processData(expList,earningList);
		DCUDebitTransformer.processData(expList,earningList);
		ChaseTransformer.processData(expList);
		DiscoverTransformer.processData(expList);
		JCPenneyTransformer.processData(expList);
		KholsTransformer.processData(expList);
		MacysTransformer.processData(expList);
		TjMaxTransformer.processData(expList);
		ZalesTransformer.processData(expList);
		WalmartTransformer.processData(expList);
		ChaseSapphireTransformer.processData(expList);
		CapitalOneTransformer.processData(expList);
		WellsFargoTransformer.processData(expList);
		BarclaysTransformer.processData(expList);
		AmexBlueCashTransformer.processData(expList);
		AmexCashMagnetTransformer.processData(expList);
		BOATravelCreditTransformer.processData(expList);
		CitiTransformer.processData(expList);
		CashTransformer.processData(expList);
		
		DataLoader.loadExpenseData(expList);
		DataLoader.loadEarningData(earningList);
		
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