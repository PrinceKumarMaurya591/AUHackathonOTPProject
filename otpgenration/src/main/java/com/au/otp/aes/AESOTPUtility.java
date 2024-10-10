package com.au.otp.aes;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.au.otp.payload.constants.AppConstants;


@Component
public class AESOTPUtility {

    @Value("${otp.aes.encryption.key}")
    private String key;

    @Value("${otp.aes.encryptioninit.vector}")
    private String initVector;

    @Value("${otp.aes.aes.mode}")
    private String mode;

    public String encrypt(String value) throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(AppConstants.UTF_CODE), "AES"),
                new IvParameterSpec(initVector.getBytes(AppConstants.UTF_CODE)));

        byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
