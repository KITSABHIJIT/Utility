package com.test.code.transform;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class TjMaxTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final String MERCHANT_DELIMITER = "           ";
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="TJ MAX CREDIT CARD";
	private static final String PAYMENT_DONE ="ONLINE PAYMENT THANK YOU";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("TjMaxFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[4].trim().contains(PAYMENT_DONE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[4].trim().split(MERCHANT_DELIMITER)[0].toUpperCase());
					exp.setExpensePlace(expenseDetails[4].trim().split(MERCHANT_DELIMITER)[1].toUpperCase());
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[3].trim()));
					if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else {
						expenseList.add(exp);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("TjMaxFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}
}
