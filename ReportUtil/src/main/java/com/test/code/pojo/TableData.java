package com.test.code.pojo;

import java.util.List;


public class TableData {
	List<List<Object>> reportData;
	int rowPosition;
	int columnPosition;
	public List<List<Object>> getReportData() {
		return reportData;
	}
	public void setReportData(List<List<Object>> reportData) {
		this.reportData = reportData;
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
}
