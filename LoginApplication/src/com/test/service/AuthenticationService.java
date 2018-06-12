package com.test.service;

import com.test.dao.UserDetailsDAO;
import com.test.entity.UserDetails;


public class AuthenticationService {

	public UserDetails validateUser(String userName, String password) {
		
		// defining the dao object
		UserDetailsDAO dao = new UserDetailsDAO();
		
		// calling the validate user method
		return dao.validateUser(userName, password);
	}
}
