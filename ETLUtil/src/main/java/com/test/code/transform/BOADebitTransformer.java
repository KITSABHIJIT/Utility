package com.test.code.transform;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class BOADebitTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="BANK OF AMERICA DEBIT CARD";
	private static final String EXCLUDED_PAYMENTS [] = {"TATA CONSULTANCY DES:DIRECT DEP ID"
			,"DISCOVER DES:E-PAYMENT ID:7562"
			,"ONLINE BANKING TRANSFER TO SAV 3088"
			,"ONLINE BANKING TRANSFER FROM SAV 3088"
			,"BANK OF AMERICA CREDIT CARD BILL"
			,"BEST BUY DES:PAYMENT ID"
			,"SEARS ONLINE DES:PAYMENT ID"
			,"KOHL'S DEPT STRS DES"
			,"COMENITY PAY IO DES"
			,"COMENITY PAY BH DES"
			,"MACYS DES:ONLINE PMT ID"
			,"TJX REWARDS MC DES"
			,"JCPENNEY CC DES"
			,"WELLS FARGO CARD DES"};
	
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("BOAdebitFile")),COMMA_DELIMITER,QUOTE_CHAR,8);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!excludeExpense(expenseDetails[1].trim())) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[1].trim().toUpperCase());
					//exp.setExpensePlace(expenseDetails[3].trim().toUpperCase());
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails[2].trim()));
					expenseList.add(exp);
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("BOAdebitFile")+"\n Error Data: "+expenseDetails.toString());
			e.printStackTrace();
		}
		return expenseList;
	}

	public static boolean excludeExpense(String expense){
		boolean exclude=false;
		for(String excludeString : EXCLUDED_PAYMENTS) {
			if(expense.toUpperCase().startsWith(excludeString)) {
				exclude=true;
				break;
			}
		}
		return exclude;
	}
	public static void main (String ... args) {
		BOADebitTransformer.processData();
	}
}
