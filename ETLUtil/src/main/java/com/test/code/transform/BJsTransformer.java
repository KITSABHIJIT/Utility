package com.test.code.transform;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.test.code.pojo.Expense;
import com.test.code.util.DateUtil;
import com.test.code.util.FileUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class BJsTransformer {
	private static final char COMMA_DELIMITER = ',';
	private static final char QUOTE_CHAR = '"';
	private static final String MODE_OF_PAYMENT ="BJS CREDIT CARD";
	private static final String PAYMENT_DONE ="PAYMENT-THANK YOU";
	public static List<Expense> processData(List<Expense> expenseList){

		CSVReader csvReader = null;
		String[] expenseDetails = null;
		try
		{
			List<String> concatinatedData=new ArrayList<String>();
			List<String> rawfileData = FileUtil.readFromFile(PropertiesUtil.getProperty("BJsFile"));
			for(int i=1;i<rawfileData.size();i++) {
				if(!rawfileData.get(i-1).contains("$") && rawfileData.get(i).contains("$")) {
					concatinatedData.add(rawfileData.get(i-1)+" "+rawfileData.get(i));
				}else if(rawfileData.get(i).contains("$")) {
					concatinatedData.add(rawfileData.get(i));
				}
			}
			FileUtil.writeToFileFromList(concatinatedData, PropertiesUtil.getProperty("BJsFileTemp"));
			/**
			 * Reading the CSV File
			 * Delimiter is comma
			 * Start reading from line 1
			 */
			csvReader = new CSVReader(new FileReader(PropertiesUtil.getProperty("BJsFileTemp")),COMMA_DELIMITER,QUOTE_CHAR,0);
			while((expenseDetails = csvReader.readNext())!=null)
			{
				if(!expenseDetails[1].trim().startsWith(PAYMENT_DONE)) {
					Expense exp = new Expense();
					exp.setModeOfPayment(MODE_OF_PAYMENT);
					exp.setTransactionDate(DateUtil.getSQLData(DateUtil.getSomeDate(expenseDetails[0].trim(), "MM/dd/yyyy")));
					exp.setMerchant(expenseDetails[1].trim().toUpperCase());
					exp.setExpensePlace(expenseDetails[2].trim().toUpperCase());
					exp.setAmount(StringUtil.getDouble(expenseDetails[4].trim().substring(1)));
					if(expenseList.contains(exp)) {
						System.out.println("Expense Record already exists: "+exp.toString());
					}else {
						expenseList.add(exp);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error file: "+PropertiesUtil.getProperty("BJsFile")+"\n Error Data: "+expenseDetails.toString());
			e.printStackTrace();
		}
		FileUtil.deleteFile(PropertiesUtil.getProperty("BJsFileTemp"));
		return expenseList;
	}
}
