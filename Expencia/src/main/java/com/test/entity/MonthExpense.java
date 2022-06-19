package com.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="month_expense")
public class MonthExpense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="COLOR")
	String color;
	
	@Column(name="MONTH")
	@Id String month;
	
	@Column(name="EXPENSE")
	double amount;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}
