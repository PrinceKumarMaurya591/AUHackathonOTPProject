package com.au.otp.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.au.otp.service.impl.OTPServiceImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestTimeValidator implements ConstraintValidator<RequestTimeValidation, Long> {

	 private static final Logger log = LoggerFactory.getLogger(RequestTimeValidator.class);
	
//	 @Value("${fastagservice.reqTime}")  // Inject the value from properties
	    private long diff=5;
	 

	    @Override
	    public boolean isValid(Long reqTime, ConstraintValidatorContext context) {
	        if (reqTime == null) {
	            return false;  // Handle null case if needed
	        }

	        long diffTime = Math.abs(System.currentTimeMillis() - reqTime);

	        log.info("System Date: {} and Request Time: {}", System.currentTimeMillis(), reqTime);
	        log.info("Difference between request time and system time: {}", diffTime);

	        return diffTime <= 60 * 1000 * diff;  // Validate the time difference
	    }
	
}
