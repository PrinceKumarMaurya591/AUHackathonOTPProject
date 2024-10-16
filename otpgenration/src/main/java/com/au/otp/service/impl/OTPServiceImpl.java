package com.au.otp.service.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.au.otp.aes.AESOTPUtility;
import com.au.otp.config.GenericWebClient;
import com.au.otp.entity.OTPEntity;
import com.au.otp.exception.OTPException;
import com.au.otp.exception.ResourceNotFoundException;
import com.au.otp.payload.constants.AppConstants;
import com.au.otp.payload.constants.OTPStatus;
import com.au.otp.payload.request.OTPDetailsRequest;
import com.au.otp.payload.request.OTPRequest;
import com.au.otp.payload.request.RegenerateOTPRequest;
import com.au.otp.payload.request.ValidateOTPRequest;
import com.au.otp.payload.response.OTPResponse;
import com.au.otp.payload.util.DateUtil;
import com.au.otp.payload.util.OTPData;
import com.au.otp.repository.OTPRepository;
import com.au.otp.service.OTPService;
import com.au.otp.service.entity.DefaultEntity;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OTPServiceImpl implements OTPService {
    private static final Logger log = LoggerFactory.getLogger(OTPServiceImpl.class);

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private AESOTPUtility aesOTPUtility;

    @Autowired
    private GenericWebClient genericWebClient;

    @Autowired
    private OTPData otpData;

    @Override
//    public OTPResponse sendOTP(OTPRequest otpRequest) throws JSONException, OTPException {
//        log.info("Inside sendOTP of OTPServiceImpl : {}", otpRequest);
//        URI uri = UriComponentsBuilder.fromUriString(otpData.getGenerateOtpUrl()).build().toUri();
//        log.info("OTP URL: {}", uri);
//        
//        OTPResponse otpResponse = null;
//        String response = genericWebClient.post(otpData.getGenerateOtpUrl(), populateOTPREQ(otpRequest), String.class);
//        log.info("Response from ESB: {}", response);
//        
//        if (!ObjectUtils.isEmpty(response)) {
//            otpResponse = populateOtpResponse(response);
//            if (otpResponse.getStatusCode().equalsIgnoreCase(OTPStatus.SUCCESS.getStatusCode())) {
//                otpResponse.setRespMessage(otpData.getOtpSuccessMsg());
//            }
//            otpResponse.setRequestId(otpRequest.getRequestId());
//            OTPEntity otpEntity = OTPEntity.build(otpRequest, otpResponse);
//            assignDefaultValues(otpRequest.getUsername(), "generateOtp", otpEntity);
//            otpRepository.save(otpEntity);
//            otpResponse = OTPResponse.buildResponse(otpResponse);
//            log.info(AppConstants.OTP_RESPONSE, response);
//            log.info("OTP RESPONSE: {}", otpResponse);
//        } else {
//            // Throw exception
//            log.info(AppConstants.EMPTY_ESB_RESPONSE, response);
//            otpResponse = populateOTPResponseTechnicalError(
//                OTPStatus.TECHNICALERROR.getStatusCode(),
//                OTPStatus.TECHNICALERROR.getReasonPhrase()
//            );
//        }
//        
//        return otpResponse;
//    }
//    
    
    
    public OTPResponse sendOTP(OTPRequest otpRequest) {
        log.info("Inside sendOTP of OTPServiceImpl : {}", otpRequest);

        // Generate a random OTP for demonstration purposes
        String generatedOtp = String.valueOf((int) (Math.random() * 900000) + 100000);
        log.info("Generated OTP: {}", generatedOtp);

        // Simulate saving OTP details to the database
        OTPEntity otpEntity = new OTPEntity();
        otpEntity.setMobile(otpRequest.getMobile());
        otpEntity.setOtp(generatedOtp);
        otpEntity.setRequestId(otpRequest.getRequestId());
        otpEntity.setCustRef(UUID.randomUUID().toString());
        otpEntity.setOtpRegenerated(true);

        // Assign default values or additional values, e.g., username and operation
        assignDefaultValues(otpRequest.getUsername(), "generateOtp", otpEntity);

        // Save the OTP entity in the database
        otpRepository.save(otpEntity);

        // Log the save operation
        log.info("OTP details saved in the database for mobile: {}", otpRequest.getMobile());

        // Create a response object
        OTPResponse response = new OTPResponse();
        response.setRespMessage("OTP generated and saved successfully");
        response.setCustRef(otpEntity.getCustRef());
        response.setStatusCode("200");
        response.setRequestStatus("SUCCESS");
        response.setRequestId(otpRequest.getRequestId());

        // Log and return the response
        log.info("OTP RESPONSE: {}", response);
        return response;
    }


    private String generateRandomOtp(String length) {
        // For simplicity, generate a random OTP of the specified length
        int otpLength = Integer.parseInt(length);
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10)); // Generate random digit from 0 to 9
        }
        return otp.toString();
    
}
    
    
    
    

//    public OTPResponse reGenerateOTP(RegenerateOTPRequest otpRequest) throws ResourceNotFoundException, JSONException, OTPException {
//        log.info("Inside reGenerateOTP of OTPServiceImpl : {}", otpRequest);
//        OTPResponse otpResponse = null;
//        OTPEntity otpEntity = null;
//        Optional<OTPEntity> otpEntityOp = otpRepository.findByCustRef(otpRequest.getCustRef());
//        
//        if (otpEntityOp.isEmpty()) {
//            log.info("custRef not found in database: {}", otpRequest.getCustRef());
//            otpResponse = populateOTPResponseTechnicalError(
//                OTPStatus.CUSTREFNOTFOUND.getStatusCode(),
//                OTPStatus.CUSTREFNOTFOUND.getReasonPhrase()
//            );
//            return otpResponse;
//        } else {
//            otpEntity = otpEntityOp.get();
//            log.info("OTP Entity: {}", otpEntity);
//        }
//        
//        otpRequest.setMobile(otpEntity.getMobile());
//        otpRequest.setCustRef(otpEntity.getCustRef());
//        otpRequest.setChannel(otpData.getChannel());
//        otpRequest.setRequestId(otpEntity.getRequestId());
//        log.info("OTP Request: {}", otpRequest);
//        
//        URI uri = UriComponentsBuilder.fromUriString(otpData.getRegenerateOtpUrl()).build().toUri();
//        log.info("OTP URL: {}", uri);
//        
//        String response = genericWebClient.post(otpData.getRegenerateOtpUrl(), otpRequest, String.class);
//        if (!ObjectUtils.isEmpty(response)) {
//            otpResponse = populateOtpResponse(response);
//            if (otpResponse.getStatusCode().equalsIgnoreCase(OTPStatus.SUCCESS.getStatusCode())) {
//                otpEntity.setTotalOtpGeneratedCount(otpEntity.getTotalOtpGeneratedCount() + 1);
//                otpEntity.setOtpRegenerated(true);
//            }
//            otpEntity.setStatusCode(otpResponse.getStatusCode()); // added
//            assignDefaultValuesUpdated(otpRequest.getUsername(), "reGenerateOtp", otpEntity);
//            otpRepository.save(otpEntity);
//            log.info("OTP RESPONSE: JSON: {}", response);
//            log.info("OTP RESPONSE MODEL: {}", otpResponse);
//        } else {
//            // Throw exception
//            log.info("Empty response from ESB {}", response);
//            otpResponse = populateOTPResponseTechnicalError(
//                OTPStatus.TECHNICALERROR.getStatusCode(),
//                OTPStatus.TECHNICALERROR.getReasonPhrase()
//            );
//        }
//        
//        return otpResponse;
//    }
//    
  
    
    
    
    
    public OTPResponse reGenerateOTP(RegenerateOTPRequest otpRequest) throws ResourceNotFoundException, JSONException, OTPException {
        log.info("Inside reGenerateOTP of OTPServiceImpl : {}", otpRequest);
        OTPResponse otpResponse = null;
        OTPEntity otpEntity = null;
        Optional<OTPEntity> otpEntityOp = otpRepository.findByCustRef(otpRequest.getCustRef());
        
        if (otpEntityOp.isEmpty()) {
            log.info("custRef not found in database: {}", otpRequest.getCustRef());
            otpResponse = populateOTPResponseTechnicalError(
                OTPStatus.CUSTREFNOTFOUND.getStatusCode(),
                OTPStatus.CUSTREFNOTFOUND.getReasonPhrase()
            );
            return otpResponse;
        } else {
            otpEntity = otpEntityOp.get();
            log.info("OTP Entity: {}", otpEntity);
        }
        
        otpRequest.setMobile(otpEntity.getMobile());
        otpRequest.setCustRef(otpEntity.getCustRef());
        otpRequest.setChannel(otpData.getChannel());
        otpRequest.setRequestId(otpEntity.getRequestId());
        log.info("OTP Request: {}", otpRequest);
        
        URI uri = UriComponentsBuilder.fromUriString(otpData.getRegenerateOtpUrl()).build().toUri();
        log.info("OTP URL: {}", uri);
        
        String response = genericWebClient.post(otpData.getRegenerateOtpUrl(), otpRequest, String.class);
        if (!ObjectUtils.isEmpty(response)) {
            otpResponse = populateOtpResponse(response);
            if (otpResponse.getStatusCode().equalsIgnoreCase(OTPStatus.SUCCESS.getStatusCode())) {
                otpEntity.setTotalOtpGeneratedCount(otpEntity.getTotalOtpGeneratedCount() + 1);
                otpEntity.setOtpRegenerated(true);
            }
            otpEntity.setStatusCode(otpResponse.getStatusCode()); // added
            assignDefaultValuesUpdated(otpRequest.getUsername(), "reGenerateOtp", otpEntity);
            otpRepository.save(otpEntity);
            log.info("OTP RESPONSE: JSON: {}", response);
            log.info("OTP RESPONSE MODEL: {}", otpResponse);
        } else {
            // Throw exception
            log.info("Empty response from ESB {}", response);
            otpResponse = populateOTPResponseTechnicalError(
                OTPStatus.TECHNICALERROR.getStatusCode(),
                OTPStatus.TECHNICALERROR.getReasonPhrase()
            );
        }
        
        return otpResponse;
    }
    
    
    
    
    
    
    
    public OTPResponse validateOTP(ValidateOTPRequest validateOtpRequest) 
            throws ResourceNotFoundException, InvalidKeyException, UnsupportedEncodingException,
                   NoSuchAlgorithmException, NoSuchPaddingException, 
                   InvalidAlgorithmParameterException, IllegalBlockSizeException, 
                   BadPaddingException, JSONException, OTPException {
    
        log.info("Inside validateOTP of OTPServiceImpl : {}", validateOtpRequest);
        
        Optional<OTPEntity> otpEntityOp = otpRepository.findByCustRef(validateOtpRequest.getCustRef());
        if (otpEntityOp.isEmpty()) {
            return handleCustRefNotFound(validateOtpRequest.getCustRef());
        }

        OTPEntity otpEntity = otpEntityOp.get();
        log.info("OTP Entity: {}", otpEntity);
        
        prepareValidateRequest(validateOtpRequest, otpEntity);
        log.info("ValidateOTPRequest Request: {}", validateOtpRequest);
        
        String response = genericWebClient.post(otpData.getValidateOtpUrl(), validateOtpRequest, String.class);
        log.info("Response from ESB: {}", response);
        
        return handleValidationResponse(response, validateOtpRequest, otpEntity);
    }

    private OTPResponse handleCustRefNotFound(String custRef) {
        log.info("custRef not found in database: {}", custRef);
        return populateOTPResponseTechnicalError(OTPStatus.CUSTREFNOTFOUND.getStatusCode(), 
                                                  OTPStatus.CUSTREFNOTFOUND.getReasonPhrase());
    }

    private void prepareValidateRequest(ValidateOTPRequest validateOtpRequest, OTPEntity otpEntity) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        validateOtpRequest.setRequestId(generateRequestId());
        validateOtpRequest.setChannel(otpData.getChannel());
        validateOtpRequest.setCustRef(validateOtpRequest.getCustRef());
        validateOtpRequest.setOtp(aesOTPUtility.encrypt(validateOtpRequest.getOtp()));
        validateOtpRequest.setMobile(otpEntity.getMobile());
    }

    private OTPResponse handleValidationResponse(String response, ValidateOTPRequest validateOtpRequest, OTPEntity otpEntity) throws JSONException, OTPException {
        if (ObjectUtils.isEmpty(response)) {
            log.info("Empty response from ESB");
            return populateOTPResponseTechnicalError(OTPStatus.TECHNICALERROR.getStatusCode(), 
                                                      OTPStatus.TECHNICALERROR.getReasonPhrase());
        }
        
        OTPResponse otpResponse = populateOtpResponse(response);
        if (otpResponse.getStatusCode().equals(OTPStatus.SUCCESS.getStatusCode())) {
            otpEntity.setOtp(validateOtpRequest.getOtp());
            otpEntity.setOtpValidated(true);
            log.info("OTP validated successfully");
        }
        
        otpEntity.setStatusCode(otpResponse.getStatusCode());
        assignDefaultValuesUpdated(validateOtpRequest.getUsername(), "ValidateOtp", otpEntity);
        otpRepository.save(otpEntity);
        
        otpResponse.setRespMessage(otpResponse.getRespMessage());
        log.info("OTP RESPONSE: {}", otpResponse);
        return otpResponse;
    }

    private OTPResponse populateOTPResponseTechnicalError(String statusCode, String respMessage) {
        OTPResponse otpResponse = new OTPResponse();
        otpResponse.setStatusCode(statusCode);
        otpResponse.setRespMessage(respMessage);
        return otpResponse;
    }

    private OTPRequest populateOTPREQ(OTPRequest otpRequest) {
        log.info("inside populateOTPREQ");
        otpRequest.setChannel(otpData.getChannel());
        otpRequest.setCustRef(generateRequestId());
        otpRequest.setMsgContent(getOTPContent(otpData.getMsgContent()));
        otpRequest.setOtplength(otpData.getOtpLength());
        otpRequest.setOtptimeout(otpData.getTimeOut());
        otpRequest.setOtptype(otpData.getOtpType());
        otpRequest.setRequestId(generateRequestId());
        
        if (ObjectUtils.isEmpty(otpRequest.getUsername())) {
            otpRequest.setUsername(otpRequest.getMobile());
        }
        
        return otpRequest;
    }

    private OTPEntity assignDefaultValues(String username, String action, OTPEntity otpEntity) {
        log.info("inside assignDefaultValues");
        DefaultEntity defaultEntity = new DefaultEntity();
        defaultEntity.setAction(action);
        defaultEntity.setCreatedBy(username);
        defaultEntity.setCreationDateTime(LocalDateTime.now());
        otpEntity.setDefaultEntity(defaultEntity);
        log.info("inside populateOtpResponse: {}", otpEntity);
        return otpEntity;
    }

    private OTPEntity assignDefaultValuesUpdated(String username, String action, OTPEntity otpEntity) {
        log.info("inside assignDefaultValuesUpdated");
        otpEntity.getDefaultEntity().setAction(action);
        otpEntity.getDefaultEntity().setUpdatedBy(username);
        otpEntity.getDefaultEntity().setUpdatedTime(LocalDateTime.now());
        return otpEntity;
    }

    private OTPResponse populateOtpResponse(String response) throws JSONException, OTPException {
        log.info("inside populateOtpResponse of OTPServiceImpl :: {}", response);
        JSONObject jsonObject = new JSONObject(response);
        
        if (jsonObject.getInt(AppConstants.STATUS_CODE) == OTPStatus.SUCCESS.getValue()) {
            log.info("OTP sent successfully");
            OTPResponse otpResponse = new OTPResponse(String.valueOf(jsonObject.get(AppConstants.STATUS_CODE)), 
                                                      jsonObject.getString("CustRef"), 
                                                      OTPStatus.SUCCESS.getStatusCode(), 
                                                      jsonObject.getString(AppConstants.REQ_STATUS), 
                                                      null);
            log.info("OTPResponse is {}", otpResponse);
            return otpResponse;
        } else {
            log.info("Inside OTP Error : {}", 
                     jsonObject.getString("RequestStatus") + ":" + 
                     jsonObject.getString(AppConstants.STATUS_DESC));
            Optional<OTPStatus> status = OTPStatus.fromValue(jsonObject.getInt("StatusCode"));
            if (status.isPresent()) {
                OTPResponse otpResponse = new OTPResponse();
                otpResponse.setStatusCode(status.get().getStatusCode());
                otpResponse.setRespMessage(jsonObject.getString(AppConstants.STATUS_DESC));
                return otpResponse;
            } else {
                log.info("Response from ESB :: {}", response);
                throw new OTPException(String.valueOf(jsonObject.getInt("StatusCode")), 
                                       jsonObject.getString("StatusDesc"));
            }
        }
    }

    private String generateRequestId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        int value = ThreadLocalRandom.current().nextInt(100, 1000);
        String requestId = value + "" + sdf.format(new Date());
        log.info("RequestId is: {}", requestId);
        return requestId;
    }

    private String getOTPContent(String rawSms) {
        String otpExpireOn = DateUtil.getTimeHHMMSSAAfterSecond(Long.parseLong(otpData.getTimeOut()));
        log.info("Expire on: {}", otpExpireOn);
        return rawSms.replace("{1}", otpExpireOn);
    }

    public boolean isOtpValidationComplete(OTPDetailsRequest otpRequest) throws OTPException {
        log.info("inside isOtpValidated of OTPServiceImpl :: {}", otpRequest);
        Optional<OTPEntity> otpEntity = otpRepository.findByCustRef(otpRequest.getOtpRefNum());
        log.debug("otp entity :: {}", otpEntity);
        
        if (otpEntity.isPresent()) {
            return otpEntity.get().isOtpValidated();
        } else {
            throw new OTPException(otpData.getNoOtpFoundError(), "otpRefNum");
        }
    }

	
	


}
