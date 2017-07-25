package com.test.pojo;


public class MQMessage {

    private String message;


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MQMessage [message=" + message + "]";
	}

}