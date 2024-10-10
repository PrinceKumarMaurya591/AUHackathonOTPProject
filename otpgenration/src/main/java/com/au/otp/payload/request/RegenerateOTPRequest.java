package com.au.otp.payload.request;

import com.au.otp.annotations.RequestTimeValidation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegenerateOTPRequest {
    private String requestId;
    private String channel;
    private String mobile;

    // @NotEmpty(message = "username should not be empty ")
    private String username;

    @NotEmpty(message = "{custid.empty.msg}")
    private String custRef;

    @Min(value = 1, message = "{dateTime.empty.msg}")
    @RequestTimeValidation(message = "{reqTime.diff.msg}")
    private Long reqTime;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCustRef() {
		return custRef;
	}

	public void setCustRef(String custRef) {
		this.custRef = custRef;
	}

	public Long getReqTime() {
		return reqTime;
	}

	public void setReqTime(Long reqTime) {
		this.reqTime = reqTime;
	}

	public RegenerateOTPRequest(String requestId, String channel, String mobile, String username,
			@NotEmpty(message = "{custid.empty.msg}") String custRef,
			@Min(value = 1, message = "{dateTime.empty.msg}") Long reqTime) {
		super();
		this.requestId = requestId;
		this.channel = channel;
		this.mobile = mobile;
		this.username = username;
		this.custRef = custRef;
		this.reqTime = reqTime;
	}
    
    
    
}
