package com.test.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="paymode_expense")
public class PaymodeExpense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="DATE")
	@Type(type="date")
	@Id Date expenseDate;
	
	@Column(name="PAYMENT_METHOD")
	@Id String paymode;
	
	@Column(name = "AMOUNT")
	private double amount;

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
	
	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

}
