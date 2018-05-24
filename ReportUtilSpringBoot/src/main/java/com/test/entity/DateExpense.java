package com.test.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="date_expense")
public class DateExpense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="DATE")
	@Type(type="date")
	@Id Date expenseDate;
	
	@Column(name = "AMOUNT")
	private double amount;

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
