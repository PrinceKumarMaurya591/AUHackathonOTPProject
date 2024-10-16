package com.au.otp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.modelmapper.ModelMapper;

import com.au.otp.payload.constants.OTPStatus;
import com.au.otp.payload.request.OTPRequest;
import com.au.otp.payload.request.ValidateOTPRequest;
import com.au.otp.payload.response.OTPResponse;
import com.au.otp.service.entity.DefaultEntity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "otp_details")
public class OTPEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cust_ref")
    private String custRef;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "channel")
    private String channel;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "otp_length")
    private String otpLength;

    @Column(name = "otp_timeout")
    private String otpTimeout;

    @Column(name = "otp_type")
    private String otpType;

    @Column(name = "otp_status_code")
    private String statusCode;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_generated_count")
    private int totalOtpGeneratedCount;

    @Column(name = "otp_validated")
    private boolean otpValidated;

    @Column(name = "otp_regenerated")
    private boolean otpRegenerated;

  

    // Getters and Setters for action

    
 
    @Embedded
    DefaultEntity defaultEntity;
    
    public static OTPEntity build(OTPRequest otpRequest, OTPResponse otpResponse) {
        OTPEntity otpEntity = new ModelMapper().map(otpRequest, OTPEntity.class);
        otpEntity.setStatusCode(otpResponse.getStatusCode());
        if (otpResponse.getStatusCode().equals(OTPStatus.SUCCESS.getStatusCode())) {
            otpEntity.setTotalOtpGeneratedCount(1);
        }
        return otpEntity;
    }

    public static OTPEntity build(ValidateOTPRequest validateOTPRequest, OTPResponse otpResponse) {
        OTPEntity otpEntity = new ModelMapper().map(validateOTPRequest, OTPEntity.class);
        otpEntity.setStatusCode(otpResponse.getStatusCode());
        return otpEntity;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustRef() {
		return custRef;
	}

	public void setCustRef(String custRef) {
		this.custRef = custRef;
	}

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

	public String getOtpLength() {
		return otpLength;
	}

	public void setOtpLength(String otpLength) {
		this.otpLength = otpLength;
	}

	public String getOtpTimeout() {
		return otpTimeout;
	}

	public void setOtpTimeout(String otpTimeout) {
		this.otpTimeout = otpTimeout;
	}

	public String getOtpType() {
		return otpType;
	}

	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public int getTotalOtpGeneratedCount() {
		return totalOtpGeneratedCount;
	}

	public void setTotalOtpGeneratedCount(int totalOtpGeneratedCount) {
		this.totalOtpGeneratedCount = totalOtpGeneratedCount;
	}

	public boolean isOtpValidated() {
		return otpValidated;
	}

	public void setOtpValidated(boolean otpValidated) {
		this.otpValidated = otpValidated;
	}

	public boolean isOtpRegenerated() {
		return otpRegenerated;
	}

	public void setOtpRegenerated(boolean otpRegenerated) {
		this.otpRegenerated = otpRegenerated;
	}

	public DefaultEntity getDefaultEntity() {
		return defaultEntity;
	}

	public void setDefaultEntity(DefaultEntity defaultEntity) {
		this.defaultEntity = defaultEntity;
	}
    
    
    
}
