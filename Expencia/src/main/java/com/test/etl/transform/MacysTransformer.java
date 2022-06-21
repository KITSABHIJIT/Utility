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

public class MacysTransformer {
	private static final char DELIMITER = '	';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="MACYS CREDIT CARD";
	private static final String PAYMENT_DONE ="PAYMENT - THANK YOU";
	private static final String INTEREST_CHARGE ="Interest Charge";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("MacysFile")),DELIMITER,QUOTE_CHAR,0);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[2].trim().startsWith(PAYMENT_DONE) && !expenseDetails[2].trim().startsWith(INTEREST_CHARGE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant("MACYS");
					exp.setExpensePlace(expenseDetails[2].trim().toUpperCase());
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("MacysFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}
}
