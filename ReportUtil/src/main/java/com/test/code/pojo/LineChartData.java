package com.test.code.pojo;

import org.jfree.data.category.DefaultCategoryDataset;


public class LineChartData {
	String title;
	String categoryAxisLabel;
	String valueAxisLabel;
	DefaultCategoryDataset dataset;
	boolean legend;
	boolean tooltips;
	boolean urls;
	int length;
	int height;
	int rowPosition;
	int columnPosition;
	boolean vertcalLabel;
	boolean displayValueOnBar;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategoryAxisLabel() {
		return categoryAxisLabel;
	}
	public void setCategoryAxisLabel(String categoryAxisLabel) {
		this.categoryAxisLabel = categoryAxisLabel;
	}
	public String getValueAxisLabel() {
		return valueAxisLabel;
	}
	public void setValueAxisLabel(String valueAxisLabel) {
		this.valueAxisLabel = valueAxisLabel;
	}
	public DefaultCategoryDataset getDataset() {
		return dataset;
	}
	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}
	public boolean isLegend() {
		return legend;
	}
	public void setLegend(boolean legend) {
		this.legend = legend;
	}
	public boolean isTooltips() {
		return tooltips;
	}
	public void setTooltips(boolean tooltips) {
		this.tooltips = tooltips;
	}
	public boolean isUrls() {
		return urls;
	}
	public void setUrls(boolean urls) {
		this.urls = urls;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getRowPosition() {
		return rowPosition;
	}
	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}
	public int getColumnPosition() {
		return columnPosition;
	}
	public void setColumnPosition(int columnPosition) {
		this.columnPosition = columnPosition;
	}
	public boolean isVertcalLabel() {
		return vertcalLabel;
	}
	public void setVertcalLabel(boolean vertcalLabel) {
		this.vertcalLabel = vertcalLabel;
	}
	public boolean isDisplayValueOnBar() {
		return displayValueOnBar;
	}
	public void setDisplayValueOnBar(boolean displayValueOnBar) {
		this.displayValueOnBar = displayValueOnBar;
	}
	
	
	
}
