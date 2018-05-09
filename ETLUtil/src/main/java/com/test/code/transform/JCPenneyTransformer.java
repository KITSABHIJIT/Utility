package com.test.code.transform;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class JCPenneyTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="JC PENNEY CARD";
	private static final String PAYMENT_DONE ="ONLINE PYMT-THANK YOU";
	public static List<Expense> processData(){

		CSVReader csvReader = null;
		String[] expenseDetails = null;
		List<Expense> expenseList = new ArrayList<Expense>();
		try
		{
			/**
			 * Reading the CSV File
			 * Delimiter is comma
			 * Start reading from line 1
			 */
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("JCPenneyFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[4].trim().startsWith(PAYMENT_DONE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant("JC PENNEY");
					exp.setExpensePlace(expenseDetails[4].trim().toUpperCase());
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[3].trim()));
					expenseList.add(exp);
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("JCPenneyFile")+"\n Error Data: "+expenseDetails.toString());
			e.printStackTrace();
		}
		return expenseList;
	}


	public static void main (String ... args) {
		JCPenneyTransformer.processData();
	}
}
