package com.test.code.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.test.code.pojo.Expense;
import com.test.code.pojo.Merchant;
import com.test.code.util.DateUtil;
import com.test.code.util.StringUtil;

public class DataTransformer {
	public static List<Expense> transformDataUsingDelimeter(Map<String,List<String>> rawData, String delimeter){
		List<Expense> expenseList=new ArrayList<Expense>();

		for (Map.Entry<String, List<String>> entry : rawData.entrySet()) {
			System.out.println("Expense File : " + entry.getKey() + " Record Count : " + entry.getValue().size());
			for(String data:entry.getValue()){
				String [] segmentList = data.split(delimeter);
				if(segmentList.length>1){
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
	
	
	public static List<Merchant> transformDataUsingDelimeterMerchant(Map<String,List<String>> rawData, String delimeter){
		List<Merchant> merchantList=new ArrayList<Merchant>();

		for (Map.Entry<String, List<String>> entry : rawData.entrySet()) {
			System.out.println("Expense File : " + entry.getKey() + " Record Count : " + entry.getValue().size());
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
	}
}
