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

public class KholsTransformer {
	private static final char DELIMITER = '	';
	private static final char DELIMITER1 = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="KOHLS CREDIT CARD";
	private static final String PAYMENT_DONE ="THANK YOU FOR THE PAYMENT";
	public static List<Expense> processData(List<Expense> expenseList){

		CSVReader csvReader = null,csvReader1 = null;
		String[] expenseDetails = null,expenseDetails1 = null;
		Date maxEntryDate=DataLoader.getMaxEntryDate(MODE_OF_PAYMENT);
		try
		{
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("KholsFile")),DELIMITER,QUOTE_CHAR,0);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[2].trim().startsWith(PAYMENT_DONE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yy")));
					exp.setMerchant("KOHLS");
					exp.setExpensePlace(expenseDetails[2].trim().toUpperCase());
					exp.setAmount(StringUtil.getDouble(expenseDetails[3].trim()));
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("KholsFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		
		try
		{
			csvReader1 = new CSVReader(new FileReader(PropertiesUtil.getProperty("KholsFile1")),DELIMITER1,QUOTE_CHAR,0);
			while((expenseDetails1 = csvReader1.readNext())!=null)
			{
				if(!expenseDetails1[3].trim().startsWith(PAYMENT_DONE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails1[1].trim(), "yyyy-MM-dd")));
					exp.setMerchant("KOHLS");
					exp.setExpensePlace(expenseDetails1[3].trim().toUpperCase());
					exp.setAmount(StringUtil.getDouble(expenseDetails1[2].trim()));
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("KholsFile1")+"\n Error Data: "+StringUtil.printArray(expenseDetails1));
			e.printStackTrace();
		}
		return expenseList;
	}
}
