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

public class BestBuyTransformer {
	private static final char COMMA_DELIMITER = '	';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="BESTBUY CARD";
	private static final String PAYMENT_DONE ="ONLINE PAYMENT";
	private static final String INTEREST_CHARGE ="INTEREST CHARGE";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("BestBuyFile")),COMMA_DELIMITER,QUOTE_CHAR,0);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[2].trim().contains(PAYMENT_DONE) && !expenseDetails[2].trim().contains(INTEREST_CHARGE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[2].trim().toUpperCase());
					exp.setAmount(StringUtil.getDouble(expenseDetails[1].trim().substring(1)));
					if(exp.getTransactionDate()==(maxEntryDate)) {
						System.out.println("Expense Record already exists on the same Date: "+exp.toString());
					}else if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else if(exp.getTransactionDate().after(maxEntryDate)) {
						expenseList.add(exp);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("BestBuyFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}
}
