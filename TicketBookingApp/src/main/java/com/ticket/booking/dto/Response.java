package com.ticket.booking.dto;

public class Response {

	
	private int statusCode;
	private String msg;
	private Object data;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return msg;
	}

	public void setStatusMsg(String statusMsg) {
		this.msg = statusMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Response(int statusCode, String statusMsg, Object data) {
		super();
		this.statusCode = statusCode;
		this.msg = statusMsg;
		this.data = data;
	}

	public Response() {
		super();
	}

}
