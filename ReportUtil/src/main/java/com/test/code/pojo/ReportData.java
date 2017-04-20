package com.test.code.pojo;

import java.util.List;

public class ReportData {
	public List<List<Object>> reportData;
	public List<PieChartData> pieData;
	public List<List<Object>> getReportData() {
		return reportData;
	}
	public void setReportData(List<List<Object>> reportData) {
		this.reportData = reportData;
	}
	public List<PieChartData> getPieData() {
		return pieData;
	}
	public void setPieData(List<PieChartData> pieData) {
		this.pieData = pieData;
	}
}
