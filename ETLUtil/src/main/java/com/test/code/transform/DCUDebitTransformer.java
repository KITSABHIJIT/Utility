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

public class DCUDebitTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String EARNING ="TATA CONSULTANCY";
	private static final String EARNING1 ="ELECTRONIC DEPOSIT - TATA CONSULTANCY";
	private static final String EARNING2 ="ELECTRONIC DEPOSIT - CITIZENS BANK";
	private static final String MODE_OF_EARNING ="DCU";
	private static final String MODE_OF_PAYMENT ="DCU DEBIT CARD";
	private static final String EXCLUDED_PAYMENTS [] = {"TATA CONSULTANCY","TATA 04"
			,"AMEX EPAYMENT ACH"
			,"DISCOVER E-PAYMENT"
			,"TRANSFER TO"
			,"TRANSFER FROM"
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
			,"KOHL'S DEPT STRS"
			,"WELLS FARGO CARDCCPYMT"
			,"CHASE DES:EPAY ID"
			,"WALMART MC WM EPAY"
			,"CAPITAL ONE ONLINE PMT"
			,"WELLS FARGO CARDCCPYMT"
			,"BARCLAYCARD US CREDITCARD"
			,"AMZ_STORECRD_PMTPAYMENT"
			,"ELECTRONIC DEPOSIT - TATA CONSULTANCY"
			,"ELECTRONIC WITHDRAWAL COMENITY PAY BH"
			,"ELECTRONIC WITHDRAWAL TJX REWARDS MC  TJX EPAY"
			,"ELECTRONIC WITHDRAWAL CITI CARD ONLINEPAYMENT"
			,"ELECTRONIC WITHDRAWAL MACYS"
			,"ELECTRONIC WITHDRAWAL AMEX EPAYMENT"
			,"ELECTRONIC WITHDRAWAL CAPITAL ONE"
			,"ELECTRONIC WITHDRAWAL BILL PAY BANK OF AMERICA"
			,"ELECTRONIC WITHDRAWAL BANK OF AMERICA"
			,"ELECTRONIC WITHDRAWAL AMZ_STORECRD"
			,"ELECTRONIC WITHDRAWAL PAYMENT FOR AMZ STORECARD"
			,"ELECTRONIC WITHDRAWAL WELLS FARGO"
			,"ELECTRONIC WITHDRAWAL DISCOVER"
			,"ELECTRONIC DEPOSIT - CITIZENS BANK"
	};

	private static final String LOAN_PAYMENTS [] = {"TRANSFER TO LOAN", "SH DRAFT","TRANSFER - 5821038-141","ELECTRONIC WITHDRAWAL PL*EAGLEROCKMANAWEB"};
	private static final String LOAN_DESCRIPTIONS [] = {"CAR LOAN", "PARKS VILLAGE RENT","CAR LOAN","PARKS VILLAGE RENT"};

	public static List<Expense> processData(List<Expense> expenseList,List<Earning> earningList){

		CSVReader csvReader = null,csvReader1 = null;
		String[] expenseDetails = null,expenseDetails1 = null;
		Date maxEntryDatePayment=DataLoader.getMaxEntryDate(MODE_OF_PAYMENT);
		Date maxEntryDateEarning=DataLoader.getMaxEntryDateEarning(MODE_OF_EARNING);

		try
		{
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
					if(exp.getTransactionDate()==(maxEntryDatePayment)) {
						System.out.println("Expense Record already exists on the same Date: "+exp.toString());
					}else if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else if(exp.getTransactionDate().after(maxEntryDatePayment)) {
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("DCUdebitFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}

		try
		{
			csvReader1 = new CSVReader(new FileReader(PropertiesUtil.getProperty("DCUdebitFile1")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails1 = csvReader1.readNext())!=null)
			{

				if(!excludeExpense(expenseDetails1[1].trim())
						&& (!excludeATMDeposits(expenseDetails1[1].trim(),-1*StringUtil.getDouble(expenseDetails1[2].trim())))
						&& !excludeP2PTransfers(expenseDetails1[1].trim())) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails1[0].trim(), "MM/dd/yyyy")));
					String includeExpense=includeExpense(expenseDetails1[1].trim());
					if(!StringUtil.isBlankOrEmpty(includeExpense)) {
						exp.setMerchant(includeExpense.toUpperCase());
					}else {
						exp.setMerchant((expenseDetails1[1].length()<100)?expenseDetails1[1].trim().toUpperCase():expenseDetails1[1].trim().toUpperCase().substring(0,99));
					}
					exp.setAmount(-1*StringUtil.getDouble(expenseDetails1[2].trim()));
					if(exp.getTransactionDate()==(maxEntryDatePayment)) {
						System.out.println("Expense Record already exists on the same Date: "+exp.toString());
					}else if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else if(exp.getTransactionDate().after(maxEntryDatePayment)) {
						expenseList.add(exp);
					}
				}else {
					if(isEarning(expenseDetails1[1].trim())) {
						Earning earning = new Earning();
						earning.setModeOfPayment(MODE_OF_EARNING);
						earning.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails1[0].trim(), "MM/dd/yyyy")));
						earning.setMerchant(expenseDetails1[1].trim().toUpperCase());
						earning.setAmount(StringUtil.getDouble(expenseDetails1[2].trim()));
						if(earning.getTransactionDate()==(maxEntryDateEarning)) {
							System.out.println("Earning Record already exists on the same Date: "+earning.toString());
						}else if(earningList.contains(earning)) {
							System.out.println("Earning Record already exists: "+earning.toString());
						}else if(earning.getTransactionDate().after(maxEntryDateEarning)) {
							earningList.add(earning);
						}
					}
				}

			}

		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("DCUdebitFile1")+"\n Error Data: "+StringUtil.printArray(expenseDetails1));
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
		if(expense.toUpperCase().startsWith(EARNING) 
				|| expense.toUpperCase().startsWith(EARNING1)
				|| expense.toUpperCase().startsWith(EARNING2)) {
			result=true;
		}
		return result;
	}
}
