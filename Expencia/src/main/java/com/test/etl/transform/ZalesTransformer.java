package com.test.etl.transform;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.util.DateUtil;
import com.test.code.util.FileUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;
import com.test.etl.load.DataLoader;
import com.test.etl.pojo.Expense;

public class ZalesTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="ZALES CREDIT CARD";
	private static final String PAYMENT_DONE ="PAYMENT-THANK YOU";
	public static List<Expense> processData(List<Expense> expenseList){

		CSVReader csvReader = null;
		String[] expenseDetails = null;
		try
		{
			List<String> concatinatedData=new ArrayList<String>();
			List<String> rawfileData = FileUtil.readFromFile(PropertiesUtil.getProperty("ZalesFile"));
			for(int i=1;i<rawfileData.size();i++) {
				if(!rawfileData.get(i-1).contains("$") && rawfileData.get(i).contains("$")) {
					concatinatedData.add(rawfileData.get(i-1)+" "+rawfileData.get(i));
				}else if(rawfileData.get(i).contains("$")) {
					concatinatedData.add(rawfileData.get(i));
				}
			}
			FileUtil.writeToFileFromList(concatinatedData, PropertiesUtil.getProperty("ZalesFileTemp"));
			/**
			 * Reading the CSV File
			 * Delimiter is comma
			 * Start reading from line 1
			 */
			Date maxEntryDate=DataLoader.getMaxEntryDate(MODE_OF_PAYMENT);
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("ZalesFileTemp")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[1].trim().contains(PAYMENT_DONE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[1].trim().toUpperCase());
					exp.setExpensePlace(expenseDetails[2].trim().toUpperCase());
					exp.setAmount(StringUtil.getDouble(expenseDetails[4].trim().substring(1)));
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("ZalesFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		FileUtil.deleteFile(PropertiesUtil.getProperty("ZalesFileTemp"));
		return expenseList;
	}
}
