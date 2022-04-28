package com.test.model;

import java.io.Serializable;
import java.util.List;

public class EncryptedContainer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<EncryptedEntry> container;

	public List<EncryptedEntry> getContainer() {
		return container;
	}

	public void setContainer(List<EncryptedEntry> container) {
		this.container = container;
	}

}
