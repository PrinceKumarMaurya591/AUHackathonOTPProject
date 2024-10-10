package com.au.otp.payload.request;

import com.au.otp.annotations.RequestTimeValidation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OTPRequest {
    private String requestId;
    private String channel;

    @NotEmpty(message = "{mobilenumber.empty.msg}")
    @Size(min = 10, max = 10, message = "{mobilenumber.length.msg}")
    private String mobile;

    // @NotEmpty(message = "username should not be empty ")
    private String username;
    private String otptype;
    private String otplength;
    private String otptimeout;
    private String msgContent;
    private String custRef;

    @Min(value = 1, message = "{dateTime.empty.msg}")
    @Max(value = 9999999999999L, message = "{dateTime.empty.msg}")
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

	public String getOtptype() {
		return otptype;
	}

	public void setOtptype(String otptype) {
		this.otptype = otptype;
	}

	public String getOtplength() {
		return otplength;
	}

	public void setOtplength(String otplength) {
		this.otplength = otplength;
	}

	public String getOtptimeout() {
		return otptimeout;
	}

	public void setOtptimeout(String otptimeout) {
		this.otptimeout = otptimeout;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
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
    
    
    
}
