package com.au.otp.exception;

import java.net.UnknownHostException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.au.otp.payload.constants.AppConstants;
import com.au.otp.payload.constants.OTPStatus;
import com.au.otp.service.impl.OTPServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j

public class ExceptionController extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.info("caught resourceNotFoundException :: {}", ex.getMessage());
        ErrorResponse message = new ErrorResponse(
            Integer.toString(HttpStatus.NOT_FOUND.value()), 
            null, 
            ex.getMessage(), 
            LocalDateTime.now(), 
            AppConstants.ERROR_TYPE_NOT_FOUND, 
            null, 
            request.getContextPath()
        );
        return message;
    }

    @ExceptionHandler(CustomeTimeoutException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public CustomTechnical timeoutException(CustomeTimeoutException ex, WebRequest request) {
        log.info("caught timeoutException :: {}", ex.getMessage());
        CustomTechnical customTechnicalException = new CustomTechnical();
        customTechnicalException.setRespMessage(OTPStatus.TECHNICALERROR.getReasonPhrase());
        customTechnicalException.setStatusCode(OTPStatus.TECHNICALERROR.getStatusCode());
        return customTechnicalException;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse globalExceptionHandler(Exception ex, WebRequest request) {
        log.info("caught generic Exception :: {}", ex.getMessage());
        ErrorResponse message = new ErrorResponse(
            Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), 
            null, 
            ex.getMessage(), 
            LocalDateTime.now(), 
            AppConstants.ERROR_TYPE_INTERNAL_SERVER, 
            null, 
            request.getContextPath()
        );
        ex.printStackTrace();
        return message;
    }

    
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
            HttpHeaders headers, 
            HttpStatus status, 
            WebRequest request) {
        log.info("caught handleMethodArgumentNotValid.");
        ErrorResponse errorResponse = new ErrorResponse(
            Integer.toString(HttpStatus.BAD_REQUEST.value()), 
            null, 
            "Invalid value for request parameter", 
            LocalDateTime.now(), 
            AppConstants.ERROR_TYPE_DATA_EXCEPTION, 
            null, 
            request.getContextPath()
        );
        ex.getBindingResult().getFieldErrors().stream()
            .forEach(error -> errorResponse.addValidationError(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebClientRequestException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public CustomTechnical globalWebClientRequestException(WebClientRequestException ex, WebRequest request) {
        log.info("caught globalWebClientRequestException and ESB internal error:: {}", ex);
        CustomTechnical customTechnicalException = new CustomTechnical();
        customTechnicalException.setRespMessage(OTPStatus.TECHNICALERROR.getReasonPhrase());
        customTechnicalException.setStatusCode(OTPStatus.TECHNICALERROR.getStatusCode());
        return customTechnicalException;
    }

    @ExceptionHandler(UnknownHostException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse globalUnknownHostException(UnknownHostException ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(
            Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), 
            null, 
            ex.getMessage(), 
            LocalDateTime.now(), 
            "INTERNAL", 
            null, 
            request.getContextPath()
        );
        ex.printStackTrace();
        return message;
    }

    @ExceptionHandler(OTPException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public CustomTechnical invalidRegenerateOtpRequest(OTPException ex, WebRequest request) {
        log.info("caught invalidRegenerateOtpRequest Exception :: {}", ex);
        CustomTechnical customTechnicalException = new CustomTechnical();
        customTechnicalException.setRespMessage(OTPStatus.TECHNICALERROR.getReasonPhrase());
        customTechnicalException.setStatusCode(OTPStatus.TECHNICALERROR.getStatusCode());
        return customTechnicalException;
    }
}
