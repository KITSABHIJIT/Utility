package com.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(Merchant.class)
@Table(name="MERCHANT")
public class Merchant implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Column(name="MERCHANT")
	@Id String merchant;
	
	@Column(name="CATEGORY")
	@Id int categoryId;
	
	@Column(name="DESCRIPTION")
	String description;
	
	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int category) {
		this.categoryId = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
