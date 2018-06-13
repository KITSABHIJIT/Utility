package com.test.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ArithmaticImpl extends UnicastRemoteObject implements Arithmatic {

	protected ArithmaticImpl() throws RemoteException {

	}

	@Override
	public int add(int a, int b) throws RemoteException {

		return a + b;
	}

	@Override
	public int subtract(int a, int b) throws RemoteException {

		return a - b;
	}

	@Override
	public int multiply(int a, int b) throws RemoteException {

		return a * b;
	}

	@Override
	public int divide(int a, int b) throws RemoteException {

		return a / b;
	}

}
