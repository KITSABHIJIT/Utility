package com.test.code.transform;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.pojo.Earning;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class DCUDebitTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String EARNING ="TATA CONSULTANCY";
	private static final String MODE_OF_EARNING ="DCU";
	private static final String MODE_OF_PAYMENT ="DCU DEBIT CARD";
	private static final String EXCLUDED_PAYMENTS [] = {"TATA CONSULTANCY"
			,"AMEX EPAYMENT ACH"
			,"DISCOVER E-PAYMENT"
			,"TRANSFER TO ACCT"
			,"TRANSFER FROM ACCT"
			,"BANK OF AMERICA BILL"
			,"COMENITY PAY IO DES"
			,"COMENITY PAY BH WEB"
			,"MACYS ONLINE PMT"
			,"TJX Rewards MC TJX EPAY"
			,"TJX REWARDS MC TJX EPAY"
			,"JCPENNEY MC JCP EPAY"
			,"CHASE CREDIT CRDEPAY"
			,"PAYMENT FOR AMZ STORECARD"
			,"BEST BUY"
			,"SEARS"
			,"KOHL'S DEPT STRS DES"
			,"WELLS FARGO CARDCCPYMT"
			,"CHASE DES:EPAY ID"};
	
	private static final String LOAN_PAYMENTS [] = {"TRANSFER TO LOAN", "SH DRAFT"};
	private static final String LOAN_DESCRIPTIONS [] = {"CAR LOAN", "PARKS VILLAGE RENT"};
	
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("DCUdebitFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if((!excludeExpense(expenseDetails[3].trim()) && !excludeExpense(expenseDetails[2].trim()))
						 && (!excludeATMDeposits(expenseDetails[3].trim(),-1*StringUtil.getDouble(expenseDetails[5].trim())))
						 && !excludeP2PTransfers(expenseDetails[3].trim())) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[1].trim(), "MM/dd/yyyy")));
					String includeExpense=includeExpense(expenseDetails[2].trim());
					if(!StringUtil.isBlankOrEmpty(includeExpense)) {
						exp.setMerchant(includeExpense.toUpperCase());
					}else {
						exp.setMerchant(expenseDetails[3].trim().toUpperCase());
					}
					//exp.setExpensePlace(expenseDetails[3].trim().toUpperCase());
					if(StringUtil.isBlankOrEmpty(expenseDetails[4])) {
						exp.setAmount(-1*StringUtil.getDouble(expenseDetails[5].trim()));
					}else {
						exp.setAmount(-1*StringUtil.getDouble(expenseDetails[4].trim()));
					}
					if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else {
						expenseList.add(exp);
					}
				}else {
					if(isEarning(expenseDetails[3].trim())) {
						Earning earning = new Earning();
						earning.setModeOfPayment(MODE_OF_EARNING);
						earning.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[1].trim(), "MM/dd/yyyy")));
						earning.setMerchant(expenseDetails[3].trim().toUpperCase());
						//earning.setEarningPlace(expenseDetails[3].trim().toUpperCase());
						if(StringUtil.isBlankOrEmpty(expenseDetails[4])) {
							earning.setAmount(StringUtil.getDouble(expenseDetails[5].trim()));
						}else {
							earning.setAmount(StringUtil.getDouble(expenseDetails[4].trim()));
						}
						if(earningList.contains(earning)) {
							System.out.println("Earning Record already exists: "+earning.toString());
						}else {
							earningList.add(earning);
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("DCUdebitFile")+"\n Error Data: "+Arrays.toString(expenseDetails));
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
	
	public static String includeExpense(String expense){
		String exclude=null;
		for(int i=0;i<LOAN_PAYMENTS.length;i++) {
			if(expense.toUpperCase().startsWith(LOAN_PAYMENTS[i])) {
				exclude=LOAN_DESCRIPTIONS[i];
				break;
			}
		}
		return exclude;
	}
	
	public static boolean excludeATMDeposits(String expense,double amount){
		boolean exclude=false;
			if(expense.toUpperCase().startsWith("ATM")) {
				if(amount <0) {
					exclude=true;
				}
			}
		return exclude;
	}
	
	public static boolean excludeP2PTransfers(String expense){
		boolean exclude=false;
			if(expense.toUpperCase().contains("P2P PAYMENT FROM:")) {
					exclude=true;
			}
		return exclude;
	}
	
	public static boolean isEarning(String expense){
		boolean result=false;
			if(expense.toUpperCase().startsWith(EARNING)) {
				result=true;
		}
		return result;
	}
}
