package com.au.otp.payload.constants;

public class AppConstants {

    public static final String OTP_LENGTH = "6";
    public static final String API_TIMEOUT = "10";

    public static final String ERROR_TYPE_INTERNAL_SERVER = "INTERNAL";
    public static final String ERROR_TYPE_BAD_REQUEST = "BAD REQUEST";
    public static final String ERROR_TYPE_NOT_FOUND = "NOT FOUND";
    public static final String ERROR_TYPE_REQUEST_TIMEOUT = "REQUEST TIMEOUT";
    public static final String ERROR_TYPE_DATA_EXCEPTION = "DATA_EXCEPTION";

    public static final String EMPTY_ESB_RESPONSE = "Empty response from esb";
    public static final String OTP_RESPONSE = "OTP RESPONSE: JSON: {}";
    public static final String REQ_STATUS = "RequestStatus";
    public static final String STATUS_CODE = "StatusCode";
    public static final String STATUS_DESC = "StatusDesc";
    public static final String UTF_CODE = "UTF-8";

    private AppConstants() {
        // Private constructor to prevent instantiation
    }
}
