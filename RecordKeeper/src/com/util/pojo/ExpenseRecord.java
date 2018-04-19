package com.util.pojo;

import java.util.Date;

public class ExpenseRecord {
	private long expenseId;
	private Date expenseDate;
	private String item;
	private String retailer;
	private double expenseAmount;
	boolean reimbursed;
	
	public long getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(long expenseId) {
		this.expenseId = expenseId;
	}
	public Date getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public double getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public boolean isReimbursed() {
		return reimbursed;
	}
	public void setReimbursed(boolean reimbursed) {
		this.reimbursed = reimbursed;
	}
	@Override
	public String toString() {
		return "ExpenseRecord [expenseId=" + expenseId + ", expenseDate="
				+ expenseDate + ", item=" + item + ", retailer=" + retailer
				+ ", expenseAmount=" + expenseAmount + ", reimbursed="
				+ reimbursed + ", getExpenseId()=" + getExpenseId()
				+ ", getExpenseDate()=" + getExpenseDate() + ", getItem()="
				+ getItem() + ", getRetailer()=" + getRetailer()
				+ ", getExpenseAmount()=" + getExpenseAmount()
				+ ", isReimbursed()=" + isReimbursed() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
