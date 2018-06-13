package com.test.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) {
		
		final int PORT = 3535;
		
		try {
			
			Arithmatic arithmatic = new ArithmaticImpl();
			
			Registry registry = LocateRegistry.createRegistry(PORT);
			
			registry.bind("arithmatic", arithmatic);
			
			System.out.println("Registry is Created and Running at "+PORT+" port...");
			
		} catch (RemoteException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
		
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
}
