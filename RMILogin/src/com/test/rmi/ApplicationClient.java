package com.test.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ApplicationClient {

	public static void main(String[] args) {
		
		Authentication authentication = null;
		
		try {
			
			// Getting Object using Remote Address
			authentication = (Authentication)Naming.lookup("rmi://localhost:5252/auth");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter the username : ");
			String userName = scanner.next();
			
			System.out.println("Enter the password : ");
			String password = scanner.next();
			
			// Invoking the Method
			boolean status = authentication.authenticate(userName, password);
			
			if(status) {
				
				System.out.println("You are an authorized user...");
				
				// Do Something....
				
			} else {
				
				System.out.println("Unauthorized Login Attempt");
			}
			
			scanner.close();
			
		} catch (MalformedURLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NotBoundException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
