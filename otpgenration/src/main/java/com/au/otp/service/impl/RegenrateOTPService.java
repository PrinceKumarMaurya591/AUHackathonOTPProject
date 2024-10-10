package com.au.otp.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.otp.controller.OTPController;
import com.au.otp.entity.OTPEntity;
import com.au.otp.payload.request.RegenerateOTPRequest;
import com.au.otp.payload.response.OTPResponse;
import com.au.otp.repository.OTPRepository;
import com.au.otp.service.entity.DefaultEntity;

@Service
public class RegenrateOTPService {
	
	private static final Logger log = LoggerFactory.getLogger(RegenrateOTPService.class);
	
	@Autowired
	OTPRepository otpRepository ;
	
	@Autowired
	DefaultEntity defaultEntity;
	
	public OTPResponse regenerateOtp(RegenerateOTPRequest request) {
	    OTPEntity otpEntityOp = otpRepository.findByCustRef(request.getCustRef())
	            .orElseThrow(() -> new RuntimeException("OTP not found"));

	    // Generate new OTP
	    String newOtpValue = generateRandomOtp();
	    LocalDateTime now = LocalDateTime.now();

	    // Create a new instance of DefaultEntity
	    DefaultEntity newDefaultEntity = new DefaultEntity();
	    newDefaultEntity.setAction("REGENERATE");
	    newDefaultEntity.setUpdatedBy(request.getUsername());
	    newDefaultEntity.setUpdatedTime(now);

	    // Check if it's a new entity or existing one
	    if (otpEntityOp.getDefaultEntity().getCreatedBy() == null) {
	        newDefaultEntity.setCreatedBy(request.getUsername());
	        newDefaultEntity.setCreationDateTime(now);
	    } else {
	        newDefaultEntity.setCreatedBy(otpEntityOp.getDefaultEntity().getCreatedBy());
	        newDefaultEntity.setCreationDateTime(otpEntityOp.getDefaultEntity().getCreationDateTime());
	    }

	    // Update OTP details
	    otpEntityOp.setOtp(newOtpValue);
	    otpEntityOp.setOtpRegenerated(true);
	    otpEntityOp.setTotalOtpGeneratedCount(otpEntityOp.getTotalOtpGeneratedCount() + 1);
	    otpEntityOp.setDefaultEntity(newDefaultEntity);

	    otpRepository.save(otpEntityOp);

	    // Build response
	    OTPResponse response = new OTPResponse();
	    response.setRespMessage("OTP regenerated successfully");
	    response.setCustRef(request.getCustRef());
	    response.setStatusCode("200");
	    response.setRequestStatus("SUCCESS");
	    response.setRequestId(request.getRequestId());

	    return OTPResponse.buildResponse(response);
	}


    private String generateRandomOtp() {
        Random random = new Random();
        return String.format("%0" + 6 + "d", random.nextInt((int) Math.pow(10, 6)));
    }

}
