package com.au.otp.payload.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@Data
public class OTPData {
	
	    private String generateOtpUrl = "http://localhost:8080/otp/generate-otp";//https://osbuat.aubankuat.in/OTPEngineRestService/genrateOTP
	    private String validateOtpUrl = "http://localhost:8080/otp/generate-otp";//https://osbuat.aubankuat.in/OTPEngineRestService/validateOTP
	    private String regenerateOtpUrl = "http://localhost:8080/otp/regenerate";//https://osbuat.aubankuat.in/OTPEngineRestService/resendOTP
	    private String channel = "dummyChannel";
	    private String otpType = "dummyOtpType";
	    private String timeOut = "30"; // Dummy timeout value in seconds
	    private String msgContent = "Dummy message content";
	    private String noOtpFoundError = "No OTP found";
	    private String otpLength = "6"; // Dummy OTP length
	    private Long connectionTimeOut = 5000L; // Dummy connection timeout in milliseconds
	    private String otpSuccessMsg = "OTP sent successfully";

	

//    @Value("${esb.otp.url.generateOtp}")
//    private String generateOtpUrl;
//
//    @Value("${esb.otp.url.validateOtp}")
//    private String validateOtpUrl;
//
//    @Value("${esb.otp.url.regenerateOtp}")
//    private String regenerateOtpUrl;
//
//    @Value("${otp.api.channel}")
//    private String channel;
//
//    @Value("${otp.api.otptype}")
//    private String otpType;
//
//    @Value("${otp.api.timeout}")
//    private String timeOut;
//
//    @Value("${fastag.recharge.otp.api.msgcontent}")
//    private String msgContent;
//
//    @Value("${otp.error.notfound}")
//    private String noOtpFoundError;
//
//    @Value("${otp.api.length}")
//    private String otpLength;
//
//    @Value("${otp.api.connectiontimeOut}")
//    private Long connectionTimeOut;
//
//    @Value("${otp.api.optsuccessMsg}")
//    private String otpSuccessMsg;

	public String getGenerateOtpUrl() {
		return generateOtpUrl;
	}

	public void setGenerateOtpUrl(String generateOtpUrl) {
		this.generateOtpUrl = generateOtpUrl;
	}

	public String getValidateOtpUrl() {
		return validateOtpUrl;
	}

	public void setValidateOtpUrl(String validateOtpUrl) {
		this.validateOtpUrl = validateOtpUrl;
	}

	public String getRegenerateOtpUrl() {
		return regenerateOtpUrl;
	}

	public void setRegenerateOtpUrl(String regenerateOtpUrl) {
		this.regenerateOtpUrl = regenerateOtpUrl;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOtpType() {
		return otpType;
	}

	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getNoOtpFoundError() {
		return noOtpFoundError;
	}

	public void setNoOtpFoundError(String noOtpFoundError) {
		this.noOtpFoundError = noOtpFoundError;
	}

	public String getOtpLength() {
		return otpLength;
	}

	public void setOtpLength(String otpLength) {
		this.otpLength = otpLength;
	}

	public Long getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(Long connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public String getOtpSuccessMsg() {
		return otpSuccessMsg;
	}

	public void setOtpSuccessMsg(String otpSuccessMsg) {
		this.otpSuccessMsg = otpSuccessMsg;
	}

	
}
