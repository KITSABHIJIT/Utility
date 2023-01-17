package com.test.util;

public class OpenDemand {
	int slNo;
	boolean isCrossChannel;
	int site;
	String soldToName;
	String teamMember;
	long articleNumber;
	String articleDescripion;
	int variance;
	long availableQty;
	int daysAged;
	int unitsToFill;
	public int getSite() {
		return site;
	}
	public void setSite(int site) {
		this.site = site;
	}
	public String getSoldToName() {
		return soldToName;
	}
	public void setSoldToName(String soldToName) {
		this.soldToName = soldToName;
	}
	public long getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(long articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getArticleDescripion() {
		return articleDescripion;
	}
	public void setArticleDescripion(String articleDescripion) {
		this.articleDescripion = articleDescripion;
	}
	public int getVariance() {
		return variance;
	}
	public void setVariance(int variance) {
		this.variance = variance;
	}
	public long getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(long availableQty) {
		this.availableQty = availableQty;
	}
	public int getDaysAged() {
		return daysAged;
	}
	public void setDaysAged(int daysAged) {
		this.daysAged = daysAged;
	}
	public int getUnitsToFill() {
		return unitsToFill;
	}
	public void setUnitsToFill(int unitsToFill) {
		this.unitsToFill = unitsToFill;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public boolean isCrossChannel() {
		return isCrossChannel;
	}
	public void setCrossChannel(boolean isCrossChannel) {
		this.isCrossChannel = isCrossChannel;
	}
	public String getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}
	@Override
	public String toString() {
		return "OpenDemand [slNo=" + slNo + ", site=" + site + ", soldToName=" + soldToName + ", articleNumber="
				+ articleNumber + ", articleDescripion=" + articleDescripion + ", variance=" + variance
				+ ", availableQty=" + availableQty + ", daysAged=" + daysAged + ", unitsToFill=" + unitsToFill + "]";
	}
	
	
}
