package com.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="expense_category")
public class ExpenseCategory implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Column(name="CATEGORY_ID")
	@Id int categoryId;
	
	@Column(name="CATEGORY")
	String category;
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
