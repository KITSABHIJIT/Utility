package com.test.controller;

import javax.crypto.Cipher;
import java.io.Serializable;

public class ChiperKeys implements Serializable
{
    private static final long serialVersionUID = 1L;
    public Cipher encryptCipher;
    public Cipher decryptCipher;
    
    public Cipher getEncryptCipher() {
        return this.encryptCipher;
    }
    
    public void setEncryptCipher(final Cipher encryptCipher) {
        this.encryptCipher = encryptCipher;
    }
    
    public Cipher getDecryptCipher() {
        return this.decryptCipher;
    }
    
    public void setDecryptCipher(final Cipher decryptCipher) {
        this.decryptCipher = decryptCipher;
    }
}