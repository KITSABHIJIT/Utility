package com.test.spicework;

import java.util.Date;

public class Tickets {
	public String OrderNumber;
	public Date createdOn;
	public int ticketNo;
	public int quantity;
	public String spoonType;
	public String customization;
	public String ShippedTo;
	public String giftMessage;
	public String ticketShop;

	public String getOrderNumber() {
		return OrderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public int getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSpoonType() {
		return spoonType;
	}
	public void setSpoonType(String spoonType) {
		this.spoonType = spoonType;
	}
	public String getCustomization() {
		return customization;
	}
	public void setCustomization(String customization) {
		this.customization = customization;
	}
	public String getShippedTo() {
		return ShippedTo;
	}
	public void setShippedTo(String shippedTo) {
		ShippedTo = shippedTo;
	}
	public String getGiftMessage() {
		return giftMessage;
	}
	public void setGiftMessage(String giftMessage) {
		this.giftMessage = giftMessage;
	}
	public String getTicketShop() {
		return ticketShop;
	}
	public void setTicketShop(String ticketShop) {
		this.ticketShop = ticketShop;
	}
	@Override
	public String toString() {
		return "Tickets [OrderNumber=" + OrderNumber + ", createdOn=" + createdOn + ", ticketNo=" + ticketNo
				+ ", quantity=" + quantity + ", spoonType=" + spoonType + ", customization=" + customization
				+ ", ShippedTo=" + ShippedTo + ", giftMessage=" + giftMessage + ", ticketShop=" + ticketShop + "]";
	}
	
}
