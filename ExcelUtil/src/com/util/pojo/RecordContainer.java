package com.util.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RecordContainer  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,BurlapRecord> burlapRecord;
	private Map<String,CakeTopperRecord> cakeTopperRecord;
	public Map<String, CakeTopperRecord> getCakeTopperRecord() {
		return cakeTopperRecord;
	}
	public void setCakeTopperRecord(Map<String, CakeTopperRecord> cakeTopperRecord) {
		this.cakeTopperRecord = cakeTopperRecord;
	}
	public Map<String, BannerRecord> getBannerRecord() {
		return bannerRecord;
	}
	public void setBannerRecord(Map<String, BannerRecord> bannerRecord) {
		this.bannerRecord = bannerRecord;
	}
	private Map<String,BannerRecord> bannerRecord;
	private List<ExpenseRecord> expenseRecord;
	public Map<String, BurlapRecord> getBurlapRecord() {
		return burlapRecord;
	}
	public void setBurlapRecord(Map<String, BurlapRecord> burlapRecord) {
		this.burlapRecord = burlapRecord;
	}
	public List<ExpenseRecord> getExpenseRecord() {
		return expenseRecord;
	}
	public void setExpenseRecord(List<ExpenseRecord> expenseRecord) {
		this.expenseRecord = expenseRecord;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
}
