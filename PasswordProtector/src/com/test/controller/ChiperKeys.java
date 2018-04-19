package com.test.controller;

import java.io.Serializable;

import javax.crypto.Cipher;

public class ChiperKeys implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Cipher encryptCipher;
	public Cipher decryptCipher;

	public Cipher getEncryptCipher() {
		return encryptCipher;
	}
	public void setEncryptCipher(Cipher encryptCipher) {
		this.encryptCipher = encryptCipher;
	}
	public Cipher getDecryptCipher() {
		return decryptCipher;
	}
	public void setDecryptCipher(Cipher decryptCipher) {
		this.decryptCipher = decryptCipher;
	}
}
