package com.test.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.test.exceptions.ErrorCodes;
import com.test.exceptions.ServiceException;

public class StoreRetreiveData {
	public static boolean storeData(Object obj,String fileName) throws ServiceException{
		boolean result=false;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
		} catch ( IOException e) {
			throw new ServiceException(ErrorCodes.DATA_INSERTION_ERROR, e);
		}finally{
			try {
				if(null!=oos){
					oos.close();
				}
				if(null!=fos){
					fos.close();
				}
			} catch (IOException e) {
				throw new ServiceException(ErrorCodes.RESOURCE_NOT_CLOSED, e);
			}
		}
		return result;
	}
	public static Object retrieveData(String fileName) throws ServiceException{
		Object obj=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			throw new ServiceException(ErrorCodes.FILE_NOT_FOUND, e);
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		try {
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (IOException e) {
			throw new ServiceException(ErrorCodes.DATA_CONVERSION_ERROR, e);
		} catch (ClassNotFoundException e) {
			throw new ServiceException(ErrorCodes.DATA_CONVERSION_ERROR, e);
		}finally{
			try {
				if(null!=ois){
					ois.close();
				}
			} catch (IOException e) {
				throw new ServiceException(ErrorCodes.RESOURCE_NOT_CLOSED, e);
			}
		}
		return obj;
	}
}
