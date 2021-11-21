package com.test.code.transform;

import java.io.FileReader;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.load.DataLoader;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class AmazonStroreTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="AMAZON STORE CARD";
	private static final String PAYMENT_DONE ="ONLINE PAYMENT - THANK YOU";
	private static final String PAYMENT_DONE1 ="ONLINE PYMT-THANK YOU";
	private static final String PAYMENT_DONE2 ="AUTOMATIC PAYMENT - THANK YOU";
	public static List<Expense> processData(List<Expense> expenseList){

		CSVReader csvReader = null;
		String[] expenseDetails = null;
		try
		{
			/**
			 * Reading the CSV File
			 * Delimiter is comma
			 * Start reading from line 1
			 */
			Date maxEntryDate=DataLoader.getMaxEntryDate(MODE_OF_PAYMENT);
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("StoreCardFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[4].trim().toUpperCase().contains(PAYMENT_DONE) 
						&& !expenseDetails[4].trim().toUpperCase().contains(PAYMENT_DONE1)
						&& !expenseDetails[4].trim().toUpperCase().contains(PAYMENT_DONE2)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));

					if(expenseDetails.length>5) {
						exp.setMerchant(expenseDetails[4].trim().toUpperCase());
						exp.setExpensePlace(expenseDetails[5].trim().toUpperCase());
					}else if(expenseDetails[4].contains("       ")){
						exp.setMerchant(expenseDetails[4].split("       ")[0].trim().toUpperCase());
						exp.setExpensePlace(expenseDetails[4].substring(expenseDetails[4].indexOf("       ")).trim().toUpperCase());	
					}else {
						exp.setMerchant(expenseDetails[4].trim().toUpperCase());
					}

					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[3].trim()));
					exp.setReferenceNo(StringUtil.trim(expenseDetails[2]));
					if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else if(exp.getTransactionDate().after(maxEntryDate)) {
						expenseList.add(exp);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("StoreCardFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}

}
