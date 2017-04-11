package com.test.code.load;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.test.code.pojo.Expense;
import com.test.code.pojo.Merchant;
import com.test.code.util.ConnectionUtil;
import com.test.code.util.PropertiesUtil;

public class DataLoader {
	public static void loadExpenseData(List<Expense> expList){
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_INSERT"));
				) {
			int i = 0;
			for (Expense exp : expList) {
				statement.setDate(1, exp.getTransactionDate());
				statement.setString(2, exp.getMerchant());
				statement.setString(3, exp.getExpensePlace());
				statement.setString(4, exp.getModeOfPayment());
				statement.setDouble(5, exp.getAmount());
				statement.setString(6, exp.getExpensePlace());
				statement.addBatch();
				i++;
				if (i % 1000 == 0 || i == expList.size()) {
					statement.executeBatch(); // Execute every 1000 items.
				}
				//System.out.println(exp.toString());
			}
			System.out.println("Total number of records added: "+expList.size());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static void loadMerchantData(List<Merchant> dataList){
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_INSERT_MERCHANT"));
				) {
			int i = 0;
			for (Merchant merchant : dataList) {
				statement.setString(1, merchant.getMerchant());
				statement.setString(2, merchant.getCategory());
				statement.setString(3, merchant.getDescription());
				statement.setString(4, merchant.getCategory());
				statement.setString(5, merchant.getDescription());
				statement.addBatch();
				i++;
				if (i % 1000 == 0 || i == dataList.size()) {
					statement.executeBatch(); // Execute every 1000 items.
				}
				//System.out.println(exp.toString());
			}
			System.out.println("Total number of records added: "+dataList.size());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void loadData(List<String> dataList){

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_REPORT_INSERT"));
				) {
			for(String data:dataList){
				String [] segmentList = data.split(PropertiesUtil.getProperty("delimeter"));
				for(int j=0;j<segmentList.length;j++){
					statement.setString(j+1, segmentList[j]);
				}
				statement.addBatch();
				System.out.println("Data added to Batch");
			}
			statement.executeBatch(); // Execute every 1000 items.
			System.out.println("Data Inserted");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
