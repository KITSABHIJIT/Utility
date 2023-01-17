package com.test.util;

import java.util.List;
import java.util.Map;

public class OpenDemandStock {
	Map<Long,Long> stockAvaialability;
	List<OpenDemand> openDemandRecords;
	
	public OpenDemandStock(Map<Long, Long> stockAvaialability, List<OpenDemand> openDemandRecords) {
		super();
		this.stockAvaialability = stockAvaialability;
		this.openDemandRecords = openDemandRecords;
	}
	public Map<Long, Long> getStockAvaialability() {
		return stockAvaialability;
	}
	public void setStockAvaialability(Map<Long, Long> stockAvaialability) {
		this.stockAvaialability = stockAvaialability;
	}
	public List<OpenDemand> getOpenDemandRecords() {
		return openDemandRecords;
	}
	public void setOpenDemandRecords(List<OpenDemand> openDemandRecords) {
		this.openDemandRecords = openDemandRecords;
	}
}
