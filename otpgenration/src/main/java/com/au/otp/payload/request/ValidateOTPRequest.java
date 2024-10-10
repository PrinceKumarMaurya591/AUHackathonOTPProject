package com.au.otp.payload.request;

import com.au.otp.annotations.RequestTimeValidation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateOTPRequest {
    private String requestId;
    private String channel;
    private String mobile;
    private String emailId;

    // @NotEmpty(message = "username should not be empty ")
    private String username;

    @NotEmpty(message = "{otp.empty.msg}")
    private String otp;

    @NotEmpty(message = "{custref.empty.msg}")
    @Size(min = 8, message = "{custref.length.msg}")
    private String custRef;

    @Min(value = 1, message = "{datetime.empty.msg}")
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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
