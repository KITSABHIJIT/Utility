package com.test.code.pojo;


public class Merchant {
	private String merchant;
	private String category;
	private String description;
	
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Merchant [merchant=" + merchant + ", category=" + category
				+ ", description=" + description + "]";
	}
	
}
