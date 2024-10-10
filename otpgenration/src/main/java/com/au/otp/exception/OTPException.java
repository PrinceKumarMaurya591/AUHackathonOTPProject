package com.au.otp.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class OTPException extends Exception {

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

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}

	public OTPException(String statusCode, String respMessage) {
		super();
		this.statusCode = statusCode;
		this.respMessage = respMessage;
	}
	
	
	
}
