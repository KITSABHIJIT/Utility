package com.test.controller;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.test.exceptions.ErrorCodes;
import com.test.exceptions.ServiceException;

public class ProtectRevealUtil {

	final static String key = "Bar12345Bar12345"; // 128 bit key
	public static final String KEY_FILE=Controller.DIR+System.getProperty("file.separator")+"PasswordProtectorKey.ser";
	

	public static void initialize() throws ServiceException{
		File dir=new File(Controller.DIR);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	public static Key getStoredKey() throws ServiceException{
		return (Key)StoreRetreiveData.retrieveData(KEY_FILE);
	}
	public static byte[] protect(String text) throws ServiceException{
		// encrypt the text
		byte[] encrypted=null;
		try {
			Cipher cipher = Cipher.getInstance("AES");
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encrypted = cipher.doFinal(text.getBytes());
		} catch (InvalidKeyException e) {
			throw new ServiceException(ErrorCodes.DATA_ENCRYPTION_ERROR, e);
		} catch (IllegalBlockSizeException e) {
			throw new ServiceException(ErrorCodes.DATA_ENCRYPTION_ERROR, e);
		} catch (BadPaddingException e) {
			throw new ServiceException(ErrorCodes.DATA_ENCRYPTION_ERROR, e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(ErrorCodes.WRONG_KEY, e);
		} catch (NoSuchPaddingException e) {
			throw new ServiceException(ErrorCodes.WRONG_KEY, e);
		}
		return encrypted;
	}


	public static String reveal(byte[] encrypted) throws ServiceException{
		String decrypted=null;     
		// decrypt the text
		try {
			Cipher cipher = Cipher.getInstance("AES");
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(encrypted));
		} catch (InvalidKeyException e) {
			throw new ServiceException(ErrorCodes.DATA_DECRYPTION_ERROR, e);
		} catch (IllegalBlockSizeException e) {
			throw new ServiceException(ErrorCodes.DATA_DECRYPTION_ERROR, e);
		} catch (BadPaddingException e) {
			throw new ServiceException(ErrorCodes.DATA_DECRYPTION_ERROR, e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(ErrorCodes.WRONG_KEY, e);
		} catch (NoSuchPaddingException e) {
			throw new ServiceException(ErrorCodes.WRONG_KEY, e);
		}
		return decrypted;
	}
	
	public static void main(String ...strings ) throws ServiceException{
		//initialize();
		byte[] encrypted=protect("abhijit");
		System.out.println(new String(encrypted));
		System.out.println(reveal(encrypted));
	}

}
