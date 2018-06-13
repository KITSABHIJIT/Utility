package com.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authentication extends Remote {

	public boolean authenticate(String userName, String password) throws RemoteException ;
}
