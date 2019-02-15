package com.test.code.load;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.code.pojo.Category;
import com.test.code.pojo.Earning;
import com.test.code.pojo.Expense;
import com.test.code.pojo.Merchant;
import com.test.code.pojo.PayMode;
import com.test.code.util.ConnectionUtil;
import com.test.code.util.PropertiesUtil;
import com.test.code.util.StringUtil;

public class DataLoader {
	
	public static void loadExpenseData(List<Expense> expList){
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statementCleanup = connection.prepareStatement(PropertiesUtil.getProperty("SQL_EXPENSE_CLEANUP"));
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_EXPENSE_INSERT"));
				) {
			statementCleanup.executeUpdate();
			int i = 0;
			for (Expense exp : expList) {
				try {
					statement.setDate(1, exp.getTransactionDate());
					statement.setString(2, StringUtil.trim(exp.getMerchant()));
					statement.setString(3, StringUtil.trim(exp.getExpensePlace()));
					statement.setString(4, exp.getModeOfPayment());
					statement.setDouble(5, exp.getAmount());
					statement.setDate(6, exp.getTransactionDate());
					statement.setString(7, exp.getMerchant());
					statement.setString(8, exp.getModeOfPayment());
					statement.setDouble(9, exp.getAmount());
					statement.executeUpdate();
					/*statement.addBatch();
				i++;
				if (i % 1000 == 0 || i == expList.size()) {
					statement.executeBatch(); // Execute every 1000 items.
				}*/
					//System.out.println(exp.toString());
				}catch(SQLException e){
					e.printStackTrace();
					System.out.println(exp.toString());
				}


			}
			System.out.println("Total number of records added: "+expList.size());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void loadEarningData(List<Earning> earningList){
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statementCleanup = connection.prepareStatement(PropertiesUtil.getProperty("SQL_EARNING_CLEANUP"));
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_EARNING_INSERT"));
				PreparedStatement addRewards = connection.prepareStatement(PropertiesUtil.getProperty("SQL_EARNING_REWARDS_ADD"));
				PreparedStatement eleminateRewards = connection.prepareStatement(PropertiesUtil.getProperty("SQL_ELEMINATE_REWARDS_EXPENSE"));
				) {
			statementCleanup.executeUpdate();
			int i = 0;
			for (Earning ear : earningList) {
				try {
					statement.setDate(1, ear.getTransactionDate());
					statement.setString(2, ear.getMerchant());
					statement.setString(3, ear.getEarningPlace());
					statement.setString(4, ear.getModeOfPayment());
					statement.setDouble(5, ear.getAmount());
					statement.setDate(6, ear.getTransactionDate());
					statement.setString(7, ear.getMerchant());
					statement.setString(8, ear.getModeOfPayment());
					statement.setDouble(9, ear.getAmount());
					statement.executeUpdate();
					/*statement.addBatch();
				i++;
				if (i % 1000 == 0 || i == expList.size()) {
					statement.executeBatch(); // Execute every 1000 items.
				}*/
					//System.out.println(exp.toString());
				}catch(SQLException e){
					e.printStackTrace();
					System.out.println(ear.toString());
				}
			}
			int rewardsAddedToEaring=addRewards.executeUpdate();
			System.out.println("Total number of rewards added to Earning: "+rewardsAddedToEaring);
			if(rewardsAddedToEaring>0) {
				int rewardsEleminatedFromExpense=eleminateRewards.executeUpdate();
				System.out.println("Total number of rewards eleminated from Expense: "+rewardsEleminatedFromExpense);
			}
			System.out.println("Total number of records added: "+earningList.size());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/*public static void loadMerchantData(List<Merchant> dataList){
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_INSERT_MERCHANT"));
				) {
			int i = 0;
			for (Merchant merchant : dataList) {
				statement.setString(1, merchant.getMerchant());
				statement.setInt(2, merchant.getCategory());
				statement.setString(3, merchant.getDescription());
				statement.setString(4, merchant.getMerchant());
				statement.setInt(5, merchant.getCategory());
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
	}*/
	public static void loadCategoryData(List<Category> dataList){
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statementCleanup = connection.prepareStatement(PropertiesUtil.getProperty("SQL_CATEGORY_CLEANUP"));
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_INSERT_CATEGORY"));
				) {
			statementCleanup.executeUpdate();
			int i = 0;
			for (Category category : dataList) {
				statement.setInt(1, category.getCategory_id());
				statement.setString(2, category.getCategory_desc());
				statement.setInt(3, category.getCategory_id());
				statement.setString(4, category.getCategory_desc());
				statement.addBatch();
				i++;
				if (i % 1000 == 0 || i == dataList.size()) {
					statement.executeBatch(); // Execute every 1000 items.
				}
				//System.out.println(exp.toString());
			}
			System.out.println("Total number of Category records added: "+dataList.size());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void loadMerchantMappingData(List<Merchant> dataList){
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statementCleanup = connection.prepareStatement(PropertiesUtil.getProperty("SQL_MERCHANT_MAPPING_CLEANUP"));
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_INSERT_MERCHANT_MAPPING"));
				) {
			statementCleanup.executeUpdate();
			int i = 0;
			for (Merchant merchant : dataList) {
				statement.setString(1, merchant.getMerchant());
				statement.setInt(2, merchant.getCategory());
				statement.setString(3, merchant.getMerchant());
				statement.setInt(4, merchant.getCategory());
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
	
	public static void loadNewMerchant(){
		List<String> newMerchants=getMerchantData();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statementCleanup = connection.prepareStatement(PropertiesUtil.getProperty("SQL_MERCHANT_CLEANUP"));
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_INSERT_NEW_MERCHANT"));
				PreparedStatement statementUpdate = connection.prepareStatement(PropertiesUtil.getProperty("SQL_NEW_MERCHANT_UPDATE"));
				) {
			statementCleanup.executeUpdate();
			int i = 0;
			for (String merchant: newMerchants) {
				statement.setString(1, merchant);
				statement.setString(2, merchant);
				statement.addBatch();
				i++;
				if (i % 1000 == 0 || i == newMerchants.size()) {
					statement.executeBatch(); // Execute every 1000 items.
				}
				//System.out.println(exp.toString());
			}
			int newMerchantUpdated=statementUpdate.executeUpdate();
			System.out.println("Total number of new Merchants added: "+newMerchants.size());
			System.out.println("Total number of new Merchants updated with category: "+newMerchantUpdated);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	
	

	public static void loadPayModeData(List<PayMode> dataList){
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_INSERT_PAYMODE"));
				) {
			int i = 0;
			for (PayMode payMode : dataList) {
				statement.setLong(1, payMode.getPaymentId());
				statement.setString(2, payMode.getPaymentType());
				statement.setString(3, payMode.getPaymentMethod());
				statement.setDouble(4, payMode.getPayLimit());
				statement.setDate(5, payMode.getActiveDate());
				statement.setString(6, payMode.getBillDate());
				statement.setLong(7, payMode.getPaymentId());
				statement.setString(8, payMode.getPaymentType());
				statement.setString(9, payMode.getPaymentMethod());
				statement.setDouble(10, payMode.getPayLimit());
				statement.setDate(11, payMode.getActiveDate());
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

	public static List<String> getMerchantData(){
		List<String> merchantList =new ArrayList<String>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(PropertiesUtil.getProperty("SQL_GET_MERCHANT"));
				) {
			try (ResultSet resultSet=statement.executeQuery()){
				while(resultSet.next()) {
					merchantList.add(resultSet.getString(1));
				}
			}
			System.out.println("Total number of New merchant fetched: "+merchantList.size());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return merchantList;
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
