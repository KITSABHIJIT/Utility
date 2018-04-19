package com.test.model;

import java.io.Serializable;

import com.test.controller.ProtectRevealUtil;
import com.test.exceptions.ServiceException;

public class EncryptedEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	byte[] entity,user,pwd;
	
	public EncryptedEntry(String entity,String user,String pwd) throws ServiceException{
		this.entity=ProtectRevealUtil.protect(entity);
		this.user=ProtectRevealUtil.protect(user);
		this.pwd=ProtectRevealUtil.protect(pwd);
	}

	public byte[] getPwd() {
		return pwd;
	}

	public byte[] getEntity() {
		return entity;
	}

	public void setEntity(byte[] entity) {
		this.entity = entity;
	}

	public byte[] getUser() {
		return user;
	}

	public void setUser(byte[] user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPwd(byte[] pwd) {
		this.pwd = pwd;
	}
}
