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

public class ChaseFreedomTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="CHASE FREEDOM CARD";
	private static final String MODE_OF_PAYMENT_TANAYA ="TANAYA-CHASE FREEDOM CARD";
	private static final String PAYMENT_DONE ="Payment Thank You - Web";
	private static final String PAYMENT_DONE1 ="PAYMENT THANK YOU - WEB";
	private static final String PAYMENT_DONE2 ="PAYMENT THANK YOU-MOBILE";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("ChaseFreedomFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!PAYMENT_DONE.equalsIgnoreCase(expenseDetails[2].trim()) 
						&& !PAYMENT_DONE1.equalsIgnoreCase(expenseDetails[2].trim())
						&& !PAYMENT_DONE2.equalsIgnoreCase(expenseDetails[2].trim())) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[2].trim().toUpperCase());
					//exp.setExpensePlace(expenseDetails[3].trim().toUpperCase());
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[5].trim()));
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("ChaseFreedomFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}

		try
		{
			/**
			 * Reading the CSV File
			 * Delimiter is comma
			 * Start reading from line 1
			 */
			Date maxEntryDate=DataLoader.getMaxEntryDate(MODE_OF_PAYMENT_TANAYA);
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("ChaseFreedomFileTanaya")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!PAYMENT_DONE.equalsIgnoreCase(expenseDetails[2].trim()) 
						&& !PAYMENT_DONE1.equalsIgnoreCase(expenseDetails[2].trim())
						&& !PAYMENT_DONE2.equalsIgnoreCase(expenseDetails[2].trim())) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT_TANAYA);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[2].trim().toUpperCase());
					//exp.setExpensePlace(expenseDetails[3].trim().toUpperCase());
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[5].trim()));
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("ChaseFreedomFileTanaya")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}
}
