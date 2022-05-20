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

public class CitiTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="CITI CARD";
	private static final String PAYMENT_DONE ="ONLINE PAYMENT - THANK YOU";
	private static final String PAYMENT_DONE1 ="MOBILE PAYMENT - THANK YOU";
	private static final String PAYMENT_DONE2 ="ONLINE PAYMENT, THANK YOU";
	private static final String PAYMENT_RECEIVED ="PAYMENT RECEIVED";
	private static final String PLACE_DELIMITER = "-";
	private static final String DATE_DELIMITER = "  ";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("CitiFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[2].trim().contains(PAYMENT_DONE) 
						&& !expenseDetails[2].trim().contains(PAYMENT_DONE1) 
						&& !expenseDetails[2].trim().contains(PAYMENT_DONE2) 
						&& !expenseDetails[2].trim().contains(PAYMENT_RECEIVED)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[1].trim().split(DATE_DELIMITER)[0], "MM/dd/yyyy")));
					exp.setMerchant((expenseDetails[2].length()<100)?expenseDetails[2].trim().toUpperCase():expenseDetails[2].trim().toUpperCase().substring(0,100));
					if(StringUtil.isBlankOrEmpty(expenseDetails[3])) {
						exp.setAmount(StringUtil.getDouble(expenseDetails[4].trim()));
					}else {
						exp.setAmount(StringUtil.getDouble(expenseDetails[3].trim()));
					}
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("CitiFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		System.out.println(Class.class.getName()+ "Completed");
		return expenseList;
	}

}
