package com.test.controller;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.security.Key;
import com.test.exceptions.ServiceException;
import java.io.File;

public class ProtectRevealUtil
{
    static final String key = "Bar12345Bar12345";
    public static final String KEY_FILE;
    
    static {
        KEY_FILE = String.valueOf(Controller.DRIVE) + System.getProperty("file.separator") + "PasswordProtectorKey.ser";
    }
    
    public static void initialize() throws ServiceException {
        final File dir = new File(Controller.DRIVE);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    public static Key getStoredKey() throws ServiceException {
        return (Key)StoreRetreiveData.retrieveData(ProtectRevealUtil.KEY_FILE);
    }
    
    public static byte[] protect(final String text) throws ServiceException {
        byte[] encrypted = null;
        try {
            final Cipher cipher = Cipher.getInstance("AES");
            final Key aesKey = new SecretKeySpec("Bar12345Bar12345".getBytes(), "AES");
            cipher.init(1, aesKey);
            encrypted = cipher.doFinal(text.getBytes());
        }
        catch (InvalidKeyException e) {
            throw new ServiceException("Not able to protect information", e);
        }
        catch (IllegalBlockSizeException e2) {
            throw new ServiceException("Not able to protect information", e2);
        }
        catch (BadPaddingException e3) {
            throw new ServiceException("Not able to protect information", e3);
        }
        catch (NoSuchAlgorithmException e4) {
            throw new ServiceException("Wrong key is being used for encryption or decryption", e4);
        }
        catch (NoSuchPaddingException e5) {
            throw new ServiceException("Wrong key is being used for encryption or decryption", e5);
        }
        return encrypted;
    }
    
    public static String reveal(final byte[] encrypted) throws ServiceException {
        String decrypted = null;
        try {
            final Cipher cipher = Cipher.getInstance("AES");
            final Key aesKey = new SecretKeySpec("Bar12345Bar12345".getBytes(), "AES");
            cipher.init(2, aesKey);
            decrypted = new String(cipher.doFinal(encrypted));
        }
        catch (InvalidKeyException e) {
            throw new ServiceException("Not able to reveal information", e);
        }
        catch (IllegalBlockSizeException e2) {
            throw new ServiceException("Not able to reveal information", e2);
        }
        catch (BadPaddingException e3) {
            throw new ServiceException("Not able to reveal information", e3);
        }
        catch (NoSuchAlgorithmException e4) {
            throw new ServiceException("Wrong key is being used for encryption or decryption", e4);
        }
        catch (NoSuchPaddingException e5) {
            throw new ServiceException("Wrong key is being used for encryption or decryption", e5);
        }
        return decrypted;
    }
    
    public static void main(final String... strings) throws ServiceException {
        final byte[] encrypted = protect("abhijit");
        System.out.println(new String(encrypted));
        System.out.println(reveal(encrypted));
    }
}