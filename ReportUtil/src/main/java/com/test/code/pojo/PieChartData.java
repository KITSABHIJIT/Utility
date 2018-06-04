package com.test.code.pojo;

import org.jfree.data.general.DefaultPieDataset;

public class PieChartData {
	String title;
	DefaultPieDataset dataset;
	boolean legend;
	boolean tooltips;
	boolean urls;
	int length;
	int height;
	int rowPosition;
	int columnPosition;
	boolean is3D;
	double startAngle;
	float foreGroundAlpha;
	double interiorGap;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public DefaultPieDataset getDataset() {
		return dataset;
	}
	public void setDataset(DefaultPieDataset dataset) {
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
	public boolean is3D() {
		return is3D;
	}
	public void setIs3D(boolean is3d) {
		is3D = is3d;
	}
	public double getStartAngle() {
		return startAngle;
	}
	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}
	public float getForeGroundAlpha() {
		return foreGroundAlpha;
	}
	public void setForeGroundAlpha(float foreGroundAlpha) {
		this.foreGroundAlpha = foreGroundAlpha;
	}
	public double getInteriorGap() {
		return interiorGap;
	}
	public void setInteriorGap(double interiorGap) {
		this.interiorGap = interiorGap;
	}
}
