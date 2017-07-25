package com.util.pojo;

public class SQLObject {
	public String tableName;
	public String columnName;
	public Double columnLength;
	public Double noOfDigit;
	public Double decimalPosition;
	public String dataType;
	public String columnText;
	public String allowNullValue;               	
	public String defaultValueSystemName;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Double getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(Double columnLength) {
		this.columnLength = columnLength;
	}
	public Double getNoOfDigit() {
		return noOfDigit;
	}
	public void setNoOfDigit(Double noOfDigit) {
		this.noOfDigit = noOfDigit;
	}
	public Double getDecimalPosition() {
		return decimalPosition;
	}
	public void setDecimalPosition(Double decimalPosition) {
		this.decimalPosition = decimalPosition;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnText() {
		return columnText;
	}
	public void setColumnText(String columnText) {
		this.columnText = columnText;
	}
	public String getAllowNullValue() {
		return allowNullValue;
	}
	public void setAllowNullValue(String allowNullValue) {
		this.allowNullValue = allowNullValue;
	}
	public String getDefaultValueSystemName() {
		return defaultValueSystemName;
	}
	public void setDefaultValueSystemName(String defaultValueSystemName) {
		this.defaultValueSystemName = defaultValueSystemName;
	}
	@Override
	public String toString() {
		return "SQLObject [tableName=" + tableName + ", columnName="
				+ columnName + ", columnLength=" + columnLength
				+ ", noOfDigit=" + noOfDigit + ", decimalPosition="
				+ decimalPosition + ", dataType=" + dataType + ", columnText="
				+ columnText + ", allowNullValue=" + allowNullValue
				+ ", defaultValueSystemName=" + defaultValueSystemName + "]";
	}                    	
	
}