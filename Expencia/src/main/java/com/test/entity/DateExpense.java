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
	@Id Date date;
	
	@Column(name = "VALUE")
	private double value;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}


}
