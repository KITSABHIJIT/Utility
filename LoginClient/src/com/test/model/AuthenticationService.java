package com.test.model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.test.rmi.Authentication;


public class AuthenticationService {
	
	private final String SERVICE_URL = "rmi://localhost:5252/auth";

	public boolean verifyUser(String userName, String password) {
		
		try {
			
			Authentication authentication = (Authentication)Naming.lookup(SERVICE_URL);
			
			return authentication.authenticate(userName, password);
			
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}
}
