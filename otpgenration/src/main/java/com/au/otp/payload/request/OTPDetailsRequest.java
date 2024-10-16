package com.au.otp.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.PropertySource;

import com.au.otp.annotations.RequestTimeValidation;

import lombok.Data;

@Data
@PropertySource("")
public class OTPDetailsRequest {
    @NotEmpty(message = "{otprefnum.empty.msg}")
    @Size(min = 8, message = "{otprefnum.length.msg}")
    private String otpRefNum;

    @Min(value = 1, message = "{dateTime.empty.msg}")
    @Max(value = 9999999999999L, message = "{dateTime.empty.msg}")
    @RequestTimeValidation(message = "{reqTime.diff.msg}")
    private Long reqTime;

	public String getOtpRefNum() {
		return otpRefNum;
	}

	public void setOtpRefNum(String otpRefNum) {
		this.otpRefNum = otpRefNum;
	}

	public Long getReqTime() {
		return reqTime;
	}

	public void setReqTime(Long reqTime) {
		this.reqTime = reqTime;
	}
    
    
    
}
