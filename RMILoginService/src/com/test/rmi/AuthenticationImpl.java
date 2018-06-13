package com.test.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class AuthenticationImpl extends UnicastRemoteObject implements Authentication {

	protected AuthenticationImpl() throws RemoteException {

	}

	@Override
	public boolean authenticate(String userName, String password)
			throws RemoteException {

		if ((userName != null && !userName.isEmpty())
				&& (password != null && !password.isEmpty())) {

			if ((userName.equalsIgnoreCase("admin"))
					&& (password.equalsIgnoreCase("admin"))) {

				return true;
			}
		}
		return false;
	}

}