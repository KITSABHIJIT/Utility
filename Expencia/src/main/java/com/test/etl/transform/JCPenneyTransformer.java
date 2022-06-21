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

public class JCPenneyTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="JC PENNEY CARD";
	private static final String PAYMENT_DONE ="ONLINE PYMT-THANK YOU";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("JCPenneyFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[4].trim().startsWith(PAYMENT_DONE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					if(expenseDetails[4].trim().toUpperCase().contains("SOLOMON") 
							|| expenseDetails[4].trim().toUpperCase().contains("STOP & SHOP")
							|| expenseDetails[4].trim().toUpperCase().contains("PHEASANT LANE")
							|| expenseDetails[4].trim().toUpperCase().contains("JCPENNEY.COM")){
						exp.setMerchant(expenseDetails[5].trim().toUpperCase()+" JC PENNEY");
					}else {
						exp.setMerchant(expenseDetails[5].trim().toUpperCase());
					}
					exp.setExpensePlace(expenseDetails[4].trim().toUpperCase());
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[3].trim()));
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("JCPenneyFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}
}
