package com.test.code.pojo;

import java.sql.Date;



public class Expense{
	private Date transactionDate;
	private String merchant;
	private String description;
	private String expensePlace;
	private String expenseCategory;
	private String modeOfPayment;
	private double amount;
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExpensePlace() {
		return expensePlace;
	}
	public void setExpensePlace(String expensePlace) {
		this.expensePlace = expensePlace;
	}
	public String getExpenseCategory() {
		return expenseCategory;
	}
	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Expense [transactionDate=" + transactionDate + ", merchant="
				+ merchant  + ", expensePlace=" + expensePlace+ ", expenseCetaegory=" + expenseCategory
				+ ", description=" + description+ ", modeOfPayment=" + modeOfPayment + ", amount=" + amount
				+ "]";
	}
	
	
	
}
