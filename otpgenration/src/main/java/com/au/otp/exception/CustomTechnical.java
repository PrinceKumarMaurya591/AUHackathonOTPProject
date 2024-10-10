package com.au.otp.exception;

import java.io.Serializable;

public class CustomTechnical implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String respMessage;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getRespMessage() {
		return respMessage;
	}
	public CustomTechnical(String statusCode, String respMessage) {
		super();
		this.statusCode = statusCode;
		this.respMessage = respMessage;
	}
	public void setRespMessage(String reasonPhrase) {
		// TODO Auto-generated method stub
		
	}
	public CustomTechnical() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
