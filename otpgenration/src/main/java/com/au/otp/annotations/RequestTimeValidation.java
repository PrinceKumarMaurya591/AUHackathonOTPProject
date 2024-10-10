package com.au.otp.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestTimeValidator.class)
public @interface RequestTimeValidation {

    String message() default "Invalid request time: it should be a maximum of 2 minutes difference";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
