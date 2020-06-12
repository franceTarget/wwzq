package com.ren.wwzq.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author: target
 * @date: 2020/6/12 16:41
 * @description:
 */
public class AESUtil {
    /**
     * 解密小程序密文
     *
     * @param sessionKey
     * @param iv
     * @param encryptedData 密文
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decryptAES(String sessionKey, String iv, String encryptedData)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        String result = null;
        byte[] encrypData = Base64.decodeBase64(encryptedData);
        byte[] ivData = Base64.decodeBase64(iv);
        byte[] sessionKeyB = Base64.decodeBase64(sessionKey);

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(sessionKeyB, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] doFinal = cipher.doFinal(encrypData);
        result = new String(doFinal);
        return result;
    }
}