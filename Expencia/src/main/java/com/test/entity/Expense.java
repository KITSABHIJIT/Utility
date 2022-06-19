package com.test.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity @IdClass(Expense.class)
@Table(name="ALL_EXPENSE")
public class Expense implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Column(name="EXPENSE_DATE")
	@Type(type="date")
	@Id Date expenseDate;

	@Column(name="MERCHANT")
	@Id String merchant;

	@Column(name="PLACE_OF_EXPENSE")
	@Id String placeOfExpense;
	
	@Column(name="CATEGORY")
	@Id String category;

	@Column(name="PAYMENT_MODE")
	@Id String paymode;

	@Column(name="AMOUNT")
	@Id double amount;

	public Date getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getPlaceOfExpense() {
		return placeOfExpense;
	}
	public void setPlaceOfExpense(String placeOfExpense) {
		this.placeOfExpense = placeOfExpense;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
