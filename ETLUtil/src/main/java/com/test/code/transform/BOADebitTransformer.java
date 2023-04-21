package com.test.code.transform;

import java.io.FileReader;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.load.DataLoader;
import com.test.code.pojo.Earning;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class BOADebitTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="BANK OF AMERICA DEBIT CARD";
	private static final String EARNING []={"TATA CONSULTANCY DES:","CITIZENS BANK DES:"};
	private static final String MODE_OF_EARNING ="BANK OF AMERICA";
	private static final String EXCLUDED_PAYMENTS [] = {"TATA CONSULTANCY DES:","TATA 04"
			,"AMERICAN EXPRESS DES:ACH PMT ID"
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
			,"WELLS FARGO CARD DES"
			,"CHASE CREDIT CRD DES:EPAY ID"
			,"CHASE DES:EPAY ID"
			,"CAPITAL ONE DES:ONLINE PMT ID"
			,"CITIZENS BANK DES:"
			,"PAYMENT FOR AMZ DES:STORECARD"};

	public static List<Expense> processData(List<Expense> expenseList,List<Earning> earningList){

		CSVReader csvReader = null;
		String[] expenseDetails = null;
		try
		{
			/**
			 * Reading the CSV File
			 * Delimiter is comma
			 * Start reading from line 1
			 */
			Date maxEntryDatePayment=DataLoader.getMaxEntryDate(MODE_OF_PAYMENT);
			Date maxEntryDateEarning=DataLoader.getMaxEntryDateEarning(MODE_OF_EARNING);
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
					if(exp.getTransactionDate()==(maxEntryDatePayment)) {
						System.out.println("Expense Record already exists on the same Date: "+exp.toString());
					}else if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else if(exp.getTransactionDate().after(maxEntryDatePayment)) {
						expenseList.add(exp);
					}
				}else {
					if(isEarning(expenseDetails[1].trim())) {
						Earning earning = new Earning();
						earning.setModeOfPayment(MODE_OF_EARNING);
						earning.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
						earning.setMerchant(expenseDetails[1].trim().toUpperCase());
						//earning.setEarningPlace(expenseDetails[3].trim().toUpperCase());
						earning.setAmount(StringUtil.getDouble(expenseDetails[2].trim()));
						if(earning.getTransactionDate()==(maxEntryDateEarning)) {
							System.out.println("Expense Record already exists on the same Date: "+earning.toString());
						}else if(earningList.contains(earning)) {
							System.out.println("Expense Record already exists: "+earning.toString());
						}else if(earning.getTransactionDate().after(maxEntryDateEarning)) {
							earningList.add(earning);
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("BOAdebitFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
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
	public static boolean isEarning(String expense){
		boolean result=false;
		for(String earing : EARNING) {
			if(expense.toUpperCase().startsWith(earing)) {
				result=true;
				break;
			}
		}
		return result;
	}
}
