package com.test.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="category_expense")
public class CategoryExpense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="DATE")
	@Type(type="date")
	@Id Date expenseDate;

	
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

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
}
