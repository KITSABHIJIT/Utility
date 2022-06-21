package com.test.etl.transform;

import java.io.FileReader;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;
import com.test.etl.load.DataLoader;
import com.test.etl.pojo.Expense;

public class AmexTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="AMEX CARD";
	private static final String PAYMENT_DONE ="ONLINE PAYMENT - THANK YOU";
	private static final String PAYMENT_DONE1 ="MOBILE PAYMENT - THANK YOU";
	private static final String PAYMENT_RECEIVED ="PAYMENT RECEIVED";
	private static final String PLACE_DELIMITER = "-";
	private static final String DATE_DELIMITER = "  ";
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
			Date maxEntryDate=DataLoader.getMaxEntryDate(MODE_OF_PAYMENT);
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("AmexFile")),COMMA_DELIMITER,QUOTE_CHAR,1);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[2].trim().contains(PAYMENT_DONE) && !expenseDetails[2].trim().contains(PAYMENT_DONE1) && !expenseDetails[2].trim().contains(PAYMENT_RECEIVED)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim().split(DATE_DELIMITER)[0], "MM/dd/yyyy")));
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
						exp.setMerchant(expenseDetails[2].trim().toUpperCase());
					}
					exp.setAmount(StringUtil.getDouble(expenseDetails[7].trim()));
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
			System.err.println("Error file: "+PropertiesUtil.getProperty("AmexFile")+"\n Error Data: "+StringUtil.printArray(expenseDetails));
			e.printStackTrace();
		}
		System.out.println(Class.class.getName()+ "Completed");
		return expenseList;
	}

}
