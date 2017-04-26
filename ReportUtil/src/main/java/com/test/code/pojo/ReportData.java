package com.test.code.pojo;

import java.util.List;

public class ReportData {
	public List<TableData> tableData;
	public List<PieChartData> pieData;
	public List<BarChartData> barData;
	public List<LineChartData> lineData;
	public List<TableData> getTableData() {
		return tableData;
	}
	public void setTableData(List<TableData> tableData) {
		this.tableData = tableData;
	}
	public List<PieChartData> getPieData() {
		return pieData;
	}
	public void setPieData(List<PieChartData> pieData) {
		this.pieData = pieData;
	}
	public List<BarChartData> getBarData() {
		return barData;
	}
	public void setBarData(List<BarChartData> barData) {
		this.barData = barData;
	}
	public List<LineChartData> getLineData() {
		return lineData;
	}
	public void setLineData(List<LineChartData> lineData) {
		this.lineData = lineData;
	}
}
