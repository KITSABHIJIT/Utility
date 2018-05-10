package com.test.code.pojo;

import java.sql.Date;



public class Expense{
	private Date transactionDate;
	private String merchant;
	private String expensePlace;
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
	public String getExpensePlace() {
		return expensePlace;
	}
	public void setExpensePlace(String expensePlace) {
		this.expensePlace = expensePlace;
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
				+ merchant  + ", expensePlace=" + expensePlace+ ", modeOfPayment=" + modeOfPayment + ", amount=" + amount
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((modeOfPayment == null) ? 0 : modeOfPayment.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (modeOfPayment == null) {
			if (other.modeOfPayment != null)
				return false;
		} else if (!modeOfPayment.equals(other.modeOfPayment))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}
	
	
	
	
}
