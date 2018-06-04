package com.test.code.pojo;

import java.sql.Date;

public class PayMode {
	
	private long paymentId;
	private String paymentType;
	private String paymentMethod;
	private double payLimit;
	private Date activeDate;
	private String billDate;
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public double getPayLimit() {
		return payLimit;
	}
	public void setPayLimit(double payLimit) {
		this.payLimit = payLimit;
	}
	public Date getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	@Override
	public String toString() {
		return "PayMode [paymentId=" + paymentId + ", paymentType="
				+ paymentType + ", paymentMethod=" + paymentMethod
				+ ", payLimit=" + payLimit + ", activeDate=" + activeDate
				+ ", billDate=" + billDate + "]";
	}
	
}
