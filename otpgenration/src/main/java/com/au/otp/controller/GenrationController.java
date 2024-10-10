package com.au.otp.controller;

import java.util.Optional;
import java.util.Random;

import org.hibernate.envers.Audited;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.otp.entity.OTPEntity;
import com.au.otp.exception.OTPException;
import com.au.otp.exception.ResourceNotFoundException;
import com.au.otp.payload.request.RegenerateOTPRequest;
import com.au.otp.payload.response.OTPResponse;
import com.au.otp.service.OTPService;
import com.au.otp.service.impl.RegenrateOTPService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/otp")
public class GenrationController {
	private static final Logger log = LoggerFactory.getLogger(GenrationController.class);
	
	@Autowired
	RegenrateOTPService regenrateOTPService;
   
    
    @PostMapping(value = "/regenerate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OTPResponse> reGenerateOTP(
            @RequestBody RegenerateOTPRequest otpReq, HttpServletRequest request) 
            throws ResourceNotFoundException, JSONException, OTPException {
        log.info("RegenerateOTPRequest details: {}", otpReq);
        OTPResponse otpResponse = regenrateOTPService.regenerateOtp(otpReq);
        return new ResponseEntity<>(otpResponse, HttpStatus.OK);
    }
    
    
    
    
    
    
    
    
	
}
