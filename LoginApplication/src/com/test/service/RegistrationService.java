package com.test.service;

import com.test.dao.UserDetailsDAO;
import com.test.entity.UserDetails;


public class RegistrationService {

	public UserDetails registerUser(UserDetails details) {
		
		// defining the dao object
		UserDetailsDAO dao = new UserDetailsDAO();
		// calling the register user method
		return dao.registerUser(details);
	}
}
