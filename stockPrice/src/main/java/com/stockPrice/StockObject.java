package com.stockPrice;

import java.math.BigDecimal;

public class StockObject {

	private String name;
	private String symbol;
	private String exchange;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal marketCap;
	private Long avgVolume;
	private Long volume;
	private BigDecimal change;
	private BigDecimal dailyChange;
	private BigDecimal nearToYearLow;
	private BigDecimal peg;
	private BigDecimal dividend;
	private BigDecimal price;
	private BigDecimal yearLow;
	private BigDecimal yearHigh;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}
	public Long getAvgVolume() {
		return avgVolume;
	}
	public void setAvgVolume(Long avgVolume) {
		this.avgVolume = avgVolume;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public BigDecimal getChange() {
		return change;
	}
	public void setChange(BigDecimal change) {
		this.change = change;
	}
	public BigDecimal getDailyChange() {
		return dailyChange;
	}
	public void setDailyChange(BigDecimal dailyChange) {
		this.dailyChange = dailyChange;
	}
	public BigDecimal getNearToYearLow() {
		return nearToYearLow;
	}
	public void setNearToYearLow(BigDecimal nearToYearLow) {
		this.nearToYearLow = nearToYearLow;
	}
	public BigDecimal getPeg() {
		return peg;
	}
	public void setPeg(BigDecimal peg) {
		this.peg = peg;
	}
	public BigDecimal getDividend() {
		return dividend;
	}
	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getYearLow() {
		return yearLow;
	}
	public void setYearLow(BigDecimal yearLow) {
		this.yearLow = yearLow;
	}
	public BigDecimal getYearHigh() {
		return yearHigh;
	}
	public void setYearHigh(BigDecimal yearHigh) {
		this.yearHigh = yearHigh;
	}
	@Override
	public String toString() {
		return "StockObject [name=" + name + ", symbol=" + symbol + ", exchange=" + exchange + ", open=" + open
				+ ", high=" + high + ", low=" + low + ", marketCap=" + marketCap + ", avgVolume=" + avgVolume
				+ ", volume=" + volume + ", change=" + change + ", nearToYearLow=" + nearToYearLow + ", peg=" + peg
				+ ", dividend=" + dividend + ", price=" + price + ", yearLow=" + yearLow + ", yearHigh=" + yearHigh
				+ "]";
	}
	
}
