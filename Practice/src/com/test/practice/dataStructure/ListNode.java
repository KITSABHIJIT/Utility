package com.test.practice.dataStructure;

public class ListNode {

	private Object value;
	
	private ListNode next;
	
	public ListNode(Object value, ListNode next) {
		super();
		this.value = value;
		this.next = next;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}
	
}
