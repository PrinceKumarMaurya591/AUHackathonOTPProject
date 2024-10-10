package com.au.otp.payload.util;

import java.util.Random;

public class CommonUtil {
	
	public static int genrateRandom() {
		return new Random().nextInt(100000);
	}
	private CommonUtil()
	{}
}
