package com.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category_expense")
public class CategoryExpense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CATEGORY")
	@Id private String category;

	@Column(name = "AMOUNT")
	private double amount;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
