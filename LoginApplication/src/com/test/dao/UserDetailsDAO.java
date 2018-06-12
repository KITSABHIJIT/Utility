package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.test.entity.UserDetails;
import com.test.util.CryptoUtil;
import com.test.util.DBUtils;

public class UserDetailsDAO {

	public UserDetails registerUser(UserDetails user) {
		final Connection connection = DBUtils.getConnection(); 
		String sql_query = "INSERT INTO USER_DETAILS (USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,EMAILID,PHONE) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql_query);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, CryptoUtil.getEncryptedPasswordMD5(user.getPassword()));
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getPhone());
			stmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		user.setValidUser(true);
		return user; 
	}

	public UserDetails validateUser(String userName, String password) {

		// getting the connection from DBUtils
		final Connection connection = DBUtils.getConnection(); 
		String sql_query = "SELECT USERID, PASSWORD, FIRST_NAME,LAST_NAME, EMAILID, PHONE FROM USER_DETAILS WHERE USERNAME = ? AND PASSWORD = ?";
		UserDetails userDetails = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql_query);
			stmt.setString(1, userName);
			stmt.setString(2, CryptoUtil.getEncryptedPasswordMD5(password));
			// executing the query
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				// defining the UserDetails object
				userDetails = new UserDetails();
				// setting the all attributes to object from database
				userDetails.setUserId(rs.getInt("USERID"));
				userDetails.setUserName(userName);
				userDetails.setFirstName(rs.getString("FIRST_NAME"));
				userDetails.setLastName(rs.getString("LAST_NAME"));
				userDetails.setEmail(rs.getString("EMAILID"));
				userDetails.setPhone(rs.getString("PHONE"));
				userDetails.setValidUser(true);
			}

		} catch(Exception ex) {

			System.out.println(ex.getMessage());
		}

		return userDetails;
	}
}
