package com.au.otp.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.au.otp.exception.OTPException;
import com.au.otp.exception.ResourceNotFoundException;
import com.au.otp.payload.request.OTPDetailsRequest;
import com.au.otp.payload.request.OTPRequest;
import com.au.otp.payload.request.RegenerateOTPRequest;
import com.au.otp.payload.request.ValidateOTPRequest;
import com.au.otp.payload.response.OTPResponse;
import com.au.otp.service.OTPService;
import com.au.otp.service.impl.OTPServiceImpl;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/otp")  // Consider adding a base path for OTP-related endpoints
@Slf4j
public class OTPController {

	private static final Logger log = LoggerFactory.getLogger(OTPController.class);

	
    @Autowired
    private OTPService otpService;

    @PostMapping(value = "/generate-otp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OTPResponse> generateOTP(
            @RequestBody  OTPRequest otpReq, HttpServletRequest request) throws JSONException, OTPException {
        log.info("OTPRequest details: {}", otpReq);
        return new ResponseEntity<>(otpService.sendOTP(otpReq), HttpStatus.OK);
    }

    @PostMapping(value = "/regenerate-otp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OTPResponse> reGenerateOTP(
            @RequestBody RegenerateOTPRequest otpReq, HttpServletRequest request) 
            throws ResourceNotFoundException, JSONException, OTPException {
        log.info("RegenerateOTPRequest details: {}", otpReq);
        OTPResponse otpResponse = otpService.reGenerateOTP(otpReq);
        return new ResponseEntity<>(otpResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/validate-otp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OTPResponse> validateOTP(
            @RequestBody(required = true) @Valid ValidateOTPRequest validateOtpRequest, HttpServletRequest request)
            throws  
                   ResourceNotFoundException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, JSONException, OTPException {
        log.info("validateOtpRequest details: {}", validateOtpRequest);
        return new ResponseEntity<>(otpService.validateOTP(validateOtpRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/otp-validation-details", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> otpEntityDetails(
            @RequestBody(required = true) @Valid OTPDetailsRequest otpDetailsRequest, HttpServletRequest request) throws OTPException {
        log.info("isOtpValidated details: {}", otpDetailsRequest);
        boolean otpEntity = otpService.isOtpValidationComplete(otpDetailsRequest);
        return new ResponseEntity<>(otpEntity, HttpStatus.OK);
    }
    
    
    
    
    
    
   
    
}
