package com.test.code.transform;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class CapitaOneTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="CAPITAL ONE CARD";
	private static final String PAYMENT_DONE ="CAPITAL ONE ONLINE PYMT";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("CapitalOneFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(null !=expenseDetails[3].trim() && !expenseDetails[3].trim().contains(PAYMENT_DONE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "yyyy-MM-dd")));
					exp.setMerchant(expenseDetails[3].trim().toUpperCase());
					//exp.setExpensePlace(expenseDetails[3].trim().toUpperCase());
					if(!StringUtil.isBlankOrEmpty(expenseDetails[5].trim())) {
						exp.setAmount(StringUtil.getDouble(expenseDetails[5].trim()));
					}else {
						exp.setAmount(-1*StringUtil.getDouble(expenseDetails[6].trim()));
					}
					//if(expenseList.contains(exp)) {
					//	System.out.println("Expense Record already exists: "+exp.toString());
					//}else {
						expenseList.add(exp);
					//}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("CapitalOneFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}
}
