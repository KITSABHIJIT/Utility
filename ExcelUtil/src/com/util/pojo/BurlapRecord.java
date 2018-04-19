package com.util.pojo;

import java.io.Serializable;
import java.util.Date;

public class BurlapRecord implements Serializable{
	private long recordId;
	private String retailer;
	private Date orderDate;
	private String orderNo;
	private String item;
	private String itemType;
	private String color;
	private String itemDesc;
	private String customization;
	private String shippingUser;
	private long quantity;
	private double shippingCharge;
	private double makingCharge;
	private String shipped;
	private String paid;
	private String taskCompleted;
	private String shippingMode;
	private String shippingBatchNo;
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getCustomization() {
		return customization;
	}
	public void setCustomization(String customization) {
		this.customization = customization;
	}
	public String getShippingUser() {
		return shippingUser;
	}
	public void setShippingUser(String userName) {
		this.shippingUser = userName;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getShippingCharge() {
		return shippingCharge;
	}
	public void setShippingCharge(double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}
	public double getMakingCharge() {
		return makingCharge;
	}
	public void setMakingCharge(double makingCharge) {
		this.makingCharge = makingCharge;
	}
	public String getShipped() {
		return shipped;
	}
	public void setShipped(String shipped) {
		this.shipped = shipped;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	public String getTaskCompleted() {
		return taskCompleted;
	}
	public void setTaskCompleted(String taskCompleted) {
		this.taskCompleted = taskCompleted;
	}
	public String getShippingMode() {
		return shippingMode;
	}
	public void setShippingMode(String shippingMode) {
		this.shippingMode = shippingMode;
	}
	public String getShippingBatchNo() {
		return shippingBatchNo;
	}
	public void setShippingBatchNo(String shippingBatchNo) {
		this.shippingBatchNo = shippingBatchNo;
	}
	@Override
	public String toString() {
		return "BurlapRecord [recordId=" + recordId + ", retailer=" + retailer
				+ ", orderDate=" + orderDate + ", orderNo=" + orderNo
				+ ", item=" + item + ", itemType=" + itemType + ", color="
				+ color + ", itemDesc=" + itemDesc + ", customization="
				+ customization + ", shippingUser=" + shippingUser
				+ ", quantity=" + quantity + ", shippingCharge="
				+ shippingCharge + ", makingCharge=" + makingCharge
				+ ", shipped=" + shipped + ", paid=" + paid
				+ ", taskCompleted=" + taskCompleted + ", shippingMode="
				+ shippingMode + ", shippingBatchNo=" + shippingBatchNo + "]";
	}
	
}
