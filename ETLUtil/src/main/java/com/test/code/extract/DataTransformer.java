package com.test.code.extract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.test.code.pojo.Category;
import com.test.code.pojo.Earning;
import com.test.code.pojo.Expense;
import com.test.code.pojo.Merchant;
import com.test.code.pojo.PayMode;
import com.test.code.transform.BOADebitTransformer;
import com.test.code.util.DateUtil;
import com.test.code.util.StringUtil;

public class DataTransformer {
	
	
	public static List<Expense> transformExpenseUsingDelimeter(Map<String,List<String>> rawData, String delimeter){
		List<Expense> expenseList=new ArrayList<Expense>();

		for (Map.Entry<String, List<String>> entry : rawData.entrySet()) {
			System.out.println("Expense File : " + entry.getKey() + " Record Count : " + entry.getValue().size());
			for(String data:entry.getValue()){
				String [] segmentList = data.split(delimeter);
				if(segmentList.length>1 && !BOADebitTransformer.excludeExpense(segmentList[2].trim())){
					Expense exp = new Expense();
					try {
						exp.setModeOfPayment(segmentList[0].trim());
						exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(segmentList[1].trim(), "MM/dd/yyyy")));
						exp.setMerchant(segmentList[2].trim());
						exp.setExpensePlace(segmentList[3].trim());
						exp.setAmount(StringUtil.getDouble(segmentList[4].trim()));
						expenseList.add(exp);
					} catch (Exception e) {
						System.err.println("Error file: "+entry.getKey()+"\n Error Data: "+data);
						e.printStackTrace();
					}
				}
			}
		}
		return expenseList;
	}
	
	public static List<Earning> transformEarningUsingDelimeter(Map<String,List<String>> rawData, String delimeter){
		List<Earning> earningList=new ArrayList<Earning>();

		for (Map.Entry<String, List<String>> entry : rawData.entrySet()) {
			System.out.println("Expense File : " + entry.getKey() + " Record Count : " + entry.getValue().size());
			for(String data:entry.getValue()){
				String [] segmentList = data.split(delimeter);
				if(segmentList.length>1 && BOADebitTransformer.isEarning(segmentList[2].trim())){
					Earning ear = new Earning();
					try {
						ear.setModeOfPayment(segmentList[0].trim());
						ear.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(segmentList[1].trim(), "MM/dd/yyyy")));
						ear.setMerchant(segmentList[2].trim());
						ear.setEarningPlace(segmentList[3].trim());
						ear.setAmount(-1*StringUtil.getDouble(segmentList[4].trim()));
						earningList.add(ear);
					} catch (Exception e) {
						System.err.println("Error file: "+entry.getKey()+"\n Error Data: "+data);
						e.printStackTrace();
					}
				}
			}
		}
		return earningList;
	}
	
	
	public static List<Merchant> transformDataUsingDelimeterMerchant(Map<String,List<String>> rawData, String delimeter){
		List<Merchant> merchantList=new ArrayList<Merchant>();

		for (Map.Entry<String, List<String>> entry : rawData.entrySet()) {
			System.out.println("Merchant Mapping File : " + entry.getKey() + " Record Count : " + entry.getValue().size());
			for(String data:entry.getValue()){
				String [] segmentList = data.split(delimeter);
				if(segmentList.length>1){
					Merchant merchant = new Merchant();
					try {
						merchant.setMerchant(segmentList[0].trim());
						merchant.setCategory(Integer.parseInt(segmentList[1].trim()));
						merchantList.add(merchant);
					} catch (Exception e) {
						System.err.println("Error file: "+entry.getKey()+"\n Error Data: "+data);
						e.printStackTrace();
					}
				}
			}
		}
		return merchantList;
	}
	
	public static List<Category> transformDataUsingDelimeterCategory(Map<String,List<String>> rawData, String delimeter){
		List<Category> categoryList=new ArrayList<Category>();

		for (Map.Entry<String, List<String>> entry : rawData.entrySet()) {
			System.out.println("Category Mapping File : " + entry.getKey() + " Record Count : " + entry.getValue().size());
			for(String data:entry.getValue()){
				String [] segmentList = data.split(delimeter);
				if(segmentList.length>1){
					Category category = new Category();
					try {
						category.setCategory_id(Integer.parseInt(segmentList[0].trim()));
						category.setCategory_desc(segmentList[1].trim());
						categoryList.add(category);
					} catch (Exception e) {
						System.err.println("Error file: "+entry.getKey()+"\n Error Data: "+data);
						e.printStackTrace();
					}
				}
			}
		}
		return categoryList;
	}
	
	/*public static List<Merchant> transformDataMerchant(){
		
		
		
		List<Merchant> merchantList=new ArrayList<Merchant>();

		for (Map.Entry<String, List<String>> entry : rawData.entrySet()) {
			System.out.println("Merchant File : " + entry.getKey() + " Record Count : " + entry.getValue().size());
			for(String data:entry.getValue()){
				String [] segmentList = data.split(delimeter);
				if(segmentList.length>1){
					Merchant merchant = new Merchant();
					try {
						merchant.setMerchant(segmentList[0].trim());
						merchant.setCategory(segmentList[1].trim());
						if(segmentList.length>2){
							merchant.setDescription(segmentList[2].trim());
						}
						merchantList.add(merchant);
					} catch (Exception e) {
						System.err.println("Error file: "+entry.getKey()+"\n Error Data: "+data);
						e.printStackTrace();
					}
				}
			}
		}
		return merchantList;
	}*/
	
	public static List<PayMode> transformDataUsingDelimeterPayMode(Map<String,List<String>> rawData, String delimeter){
		List<PayMode> payModeList=new ArrayList<PayMode>();

		for (Map.Entry<String, List<String>> entry : rawData.entrySet()) {
			System.out.println("Paymode File : " + entry.getKey() + " Record Count : " + entry.getValue().size());
			for(String data:entry.getValue()){
				String [] segmentList = data.split(delimeter);
				if(segmentList.length>1){
					PayMode payMode = new PayMode();
					try {
						payMode.setPaymentId(StringUtil.getStrToLong(segmentList[0].trim()));
						payMode.setPaymentType(segmentList[1].trim());
						payMode.setPaymentMethod(segmentList[2].trim());
						payMode.setPayLimit(StringUtil.getDouble(segmentList[3].trim()));
						payMode.setActiveDate(DateUtil.getSQLData(DateUtil.getSomeDate(segmentList[4].trim(), "MM/dd/yyyy")));
						payMode.setBillDate(segmentList[5].trim());
						payModeList.add(payMode);
					} catch (Exception e) {
						System.err.println("Error file: "+entry.getKey()+"\n Error Data: "+data);
						e.printStackTrace();
					}
				}
			}
		}
		return payModeList;
	}
}
