package com.au.otp.annotations;

import jakarta.validation.Payload;

public @interface UtcTimeStampValidation {
	
	public String message() default"Invalid request time: it should be in proper formate of UTC";
	Class<?>[] groups()default{};
	Class<? extends Payload>[] payload() default{};

}
