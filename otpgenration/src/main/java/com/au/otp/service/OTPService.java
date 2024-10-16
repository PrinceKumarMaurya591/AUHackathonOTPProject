package com.au.otp.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.JSONException;

import com.au.otp.exception.OTPException;
import com.au.otp.exception.ResourceNotFoundException;
import com.au.otp.payload.request.OTPDetailsRequest;
import com.au.otp.payload.request.OTPRequest;
import com.au.otp.payload.request.RegenerateOTPRequest;
import com.au.otp.payload.request.ValidateOTPRequest;
import com.au.otp.payload.response.OTPResponse;


public interface OTPService {
	
	OTPResponse sendOTP(OTPRequest otpRequest) throws JSONException, OTPException;
	OTPResponse validateOTP(ValidateOTPRequest otpRequest) throws ResourceNotFoundException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, JSONException, OTPException;
	OTPResponse reGenerateOTP(RegenerateOTPRequest otpRequest) throws ResourceNotFoundException, JSONException, OTPException;
	boolean isOtpValidationComplete(OTPDetailsRequest otpRequest)throws OTPException;

}
