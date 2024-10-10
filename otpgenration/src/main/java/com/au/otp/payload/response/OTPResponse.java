package com.au.otp.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OTPResponse {
    private String respMessage; // statusDesc
    private String custRef;
    private String statusCode;
    private String requestStatus;
    private String requestId;

    public static OTPResponse buildResponse(OTPResponse response) {
        response.setRequestStatus(null);
        response.setRequestId(null);
        return response;
    }

	public String getRespMessage() {
		return respMessage;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}

	public String getCustRef() {
		return custRef;
	}

	public void setCustRef(String custRef) {
		this.custRef = custRef;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public OTPResponse(String respMessage, String custRef, String statusCode, String requestStatus, String requestId) {
		super();
		this.respMessage = respMessage;
		this.custRef = custRef;
		this.statusCode = statusCode;
		this.requestStatus = requestStatus;
		this.requestId = requestId;
	}

	public OTPResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
