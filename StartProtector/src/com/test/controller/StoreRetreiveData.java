package com.test.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.test.exceptions.ServiceException;

public class StoreRetreiveData
{
    public static boolean storeData(final Object obj, final String fileName) throws ServiceException {
        final boolean result = false;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        }
        catch (IOException e) {
            throw new ServiceException("Cannot add information to Data File", e);
        }
        finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException e2) {
                throw new ServiceException("Resource not closed", e2);
            }
        }
        try {
            if (oos != null) {
                oos.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
        catch (IOException e2) {
            throw new ServiceException("Resource not closed", e2);
        }
        return result;
    }
    
    public static Object retrieveData(final String fileName) throws ServiceException {
        Object obj = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(fileName);
        }
        catch (FileNotFoundException e) {
            throw new ServiceException("Data File not present", e);
        }
        final BufferedInputStream bis = new BufferedInputStream(fis);
        try {
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        }
        catch (IOException e2) {
            throw new ServiceException("Cannot fetch readable information from Data File", e2);
        }
        catch (ClassNotFoundException e3) {
            throw new ServiceException("Cannot fetch readable information from Data File", e3);
        }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            }
            catch (IOException e4) {
                throw new ServiceException("Resource not closed", e4);
            }
        }
        try {
            if (ois != null) {
                ois.close();
            }
        }
        catch (IOException e4) {
            throw new ServiceException("Resource not closed", e4);
        }
        return obj;
    }
}