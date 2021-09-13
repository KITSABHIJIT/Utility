package com.test.code.transform;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class AmexBlueCashTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="AMEX CARD";
	private static final String PAYMENT_DONE ="ONLINE PAYMENT - THANK YOU";
	private static final String PAYMENT_DONE1 ="MOBILE PAYMENT - THANK YOU";
	private static final String PAYMENT_RECEIVED ="PAYMENT RECEIVED";
	private static final String PLACE_DELIMITER = "-";
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
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("AmexCashMagnetFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[1].trim().contains(PAYMENT_DONE) && !expenseDetails[1].trim().contains(PAYMENT_DONE1) && !expenseDetails[1].trim().contains(PAYMENT_RECEIVED)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yy")));
					if(expenseDetails[2].trim().contains(PLACE_DELIMITER)) {
						String [] MerchantPlace =expenseDetails[2].trim().split(PLACE_DELIMITER);
						String merchant="";
						for(int i=0;i<MerchantPlace.length-1;i++) {
							merchant=merchant+MerchantPlace[i];
						}
						exp.setMerchant(merchant.toUpperCase());
						if(MerchantPlace.length>1) {
							exp.setExpensePlace(MerchantPlace[MerchantPlace.length-1].toUpperCase());
						}else {
							System.out.println("Merchant doesnt have a Place: "+ MerchantPlace[0]);
						}
					}else {
						exp.setMerchant(expenseDetails[1].trim().toUpperCase());
					}
					exp.setAmount(StringUtil.getDouble(expenseDetails[4].trim()));
					if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else {
						expenseList.add(exp);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("AmexCashMagnetFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		return expenseList;
	}

}
