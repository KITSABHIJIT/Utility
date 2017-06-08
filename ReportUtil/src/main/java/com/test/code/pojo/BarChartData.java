package com.test.code.pojo;

import org.jfree.data.category.DefaultCategoryDataset;


public class BarChartData {
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
	boolean verticalLabel;
	boolean is3D;
	boolean displayValueOnBar;
	boolean verticalBar;
	
	
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
	public boolean isVerticalLabel() {
		return verticalLabel;
	}
	public void setVerticalLabel(boolean vertcalLabel) {
		this.verticalLabel = vertcalLabel;
	}
	public boolean isIs3D() {
		return is3D;
	}
	public void setIs3D(boolean is3d) {
		is3D = is3d;
	}
	public boolean isDisplayValueOnBar() {
		return displayValueOnBar;
	}
	public void setDisplayValueOnBar(boolean displayValueOnBar) {
		this.displayValueOnBar = displayValueOnBar;
	}
	public boolean isVerticalBar() {
		return verticalBar;
	}
	public void setVerticalBar(boolean verticalBar) {
		this.verticalBar = verticalBar;
	}
	
	
	
}
