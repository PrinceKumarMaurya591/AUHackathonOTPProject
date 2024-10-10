package com.au.otp.payload.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateUtil {

    public static int generateRandom() {
        return new Random().nextInt(100000);
    }

    public static String getTimeHHMMSSAAfterSecond(long addSeconds) {
        LocalDateTime currentTime = LocalDateTime.now();
        currentTime = currentTime.plusSeconds(addSeconds);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return currentTime.format(formatter);
    }

    private DateUtil() {
        // Private constructor to prevent instantiation
    }
}
