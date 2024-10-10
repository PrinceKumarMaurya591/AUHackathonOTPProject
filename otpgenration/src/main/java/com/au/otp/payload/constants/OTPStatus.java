package com.au.otp.payload.constants;

import java.util.Arrays;
import java.util.Optional;

public enum OTPStatus {
    SUCCESS(100, "1000", "Success"),
    FAILED(101, "1001", "Failed"),
    INVALIDREQUEST(102, "1002", "Invalid request format"),
    INAVLIDAUTH(103, "1003", "Invalid authentication key"),
    NOTENABLED(104, "1004", "The Otp feature not enabled"),
    INVALIDPARAMETERS(105, "1005", "Requested parameters are missing"),
    INVALIDOTP(106, "1006", "Wrong OTP"),
    MAXNOTRIED(107, "1007", "Maximum number of tries for this OTP has been reached. Please generate a new one"),
    OTPEXPIRED(108, "1008", "OTP expired"),
    MAXRESEND(109, "1009", "Maximum number of resends for this OTP has been reached. Please generate a new one"),
    OTPNOTEXISTS(110, "1010", "OTP does not exist or has been exhausted"),
    
    // These enum constants are used for our internal API response code and response message
    CUSTREFNOTFOUND(1098, "1098", "Please enter valid customer reference"),
    TECHNICALERROR(1099, "1099", "Technical error. Please try again after some time.");

    private final int value;
    private final String statusCode;
    private final String reasonPhrase;

    private OTPStatus(int value, String statusCode, String reasonPhrase) {
        this.value = value;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public static Optional<OTPStatus> fromValue(int value) {
        return Arrays.stream(values())
                .filter(bl -> bl.value == value)
                .findFirst();
    }
}
