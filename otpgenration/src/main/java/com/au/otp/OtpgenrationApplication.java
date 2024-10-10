package com.au.otp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//@EnableCaching 
@SpringBootApplication
public class OtpgenrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpgenrationApplication.class, args);
	}

}
