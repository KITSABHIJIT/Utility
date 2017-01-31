package com.xmlUtility.test;

public class NodeEntity {
	String nodeName;
	int startPosition;
	int endPosition;
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
	public int getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
	@Override
	public String toString() {
		return "NodeEntity [nodeName=" + nodeName + ", startPosition="
				+ startPosition + ", endPosition=" + endPosition + "]";
	}
	
}
