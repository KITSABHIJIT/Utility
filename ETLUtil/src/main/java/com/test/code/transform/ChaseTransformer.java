package com.test.code.transform;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class ChaseTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="AMAZON CHASE CARD";
	private static final String PAYMENT_DONE ="Payment Thank You - Web";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("ChaseFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!PAYMENT_DONE.equals(expenseDetails[3].trim())) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[1].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[3].trim().toUpperCase());
					//exp.setExpensePlace(expenseDetails[3].trim().toUpperCase());
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[4].trim()));
					if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else {
						expenseList.add(exp);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("ChaseFile")+"\n Error Data: "+expenseDetails.toString());
			e.printStackTrace();
		}
		return expenseList;
	}
}