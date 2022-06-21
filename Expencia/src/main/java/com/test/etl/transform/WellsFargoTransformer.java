package com.test.etl.transform;

import java.io.FileReader;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;
import com.test.etl.load.DataLoader;
import com.test.etl.pojo.Expense;

public class WellsFargoTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="WELLS FARGO CREDIT CARD";
	private static final String PAYMENT_DONE ="ONLINE ACH PAYMENT - THANK YOU";
	private static final String PAYMENT_DONE1 ="ONLINE PAYMENT WEST DES MOIN";
	private static final String PAYMENT_DONE2 ="ONLINE ACH PAYMENT WEST DES MOIN";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("WellsFargoFile")),COMMA_DELIMITER,QUOTE_CHAR,0);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!PAYMENT_DONE.equals(expenseDetails[4].trim())
						&& !PAYMENT_DONE1.equals(expenseDetails[4].trim())
						&& !PAYMENT_DONE2.equals(expenseDetails[4].trim())) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[4].trim().toUpperCase());
					//exp.setExpensePlace(expenseDetails[5].trim().toUpperCase());
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[1].trim()));
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("WellsFargoFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}

}
