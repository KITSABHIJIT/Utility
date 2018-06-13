package com.test.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		try {
			
			Arithmatic arithmatic = (Arithmatic)Naming.lookup("rmi://localhost:3535/arithmatic");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter the first number : ");
			int a = scanner.nextInt();
			
			System.out.println("Enter the second number : ");
			int b = scanner.nextInt();
			
			System.out.println("Arithmatic Result of ("+a+" & "+b+")\n----------------------------");
			System.out.println("Addition of "+a+" and "+b+" is : "+arithmatic.add(a, b));
			System.out.println("Subtraction of "+a+" and "+b+" is : "+arithmatic.subtract(a, b));
			System.out.println("Multiplication of "+a+" and "+b+" is : "+arithmatic.multiply(a, b));
			System.out.println("Division of "+a+" and "+b+" is : "+arithmatic.divide(a, b));
			
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
