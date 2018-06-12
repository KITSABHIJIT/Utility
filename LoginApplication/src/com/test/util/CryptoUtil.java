package com.test.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.mindrot.jbcrypt.BCrypt;

public class CryptoUtil {
	
	public static String getEncryptedPassword(String passPhrase) {
		return BCrypt.hashpw(passPhrase, BCrypt.gensalt(12));
	}
	
	public static boolean isValid(String originalPassword,String generatedSecuredPasswordHash) {
		return BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
	}
	
	public static String getEncryptedPasswordMD5(String passPhrase) {
		String generatedPassword=null;
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passPhrase.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		return generatedPassword;
	}
}
