package com.ren.wwzq.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @author: target
 * @date: 2020/6/12 16:39
 * @description:
 */
public class SHA1Util {

    public static String encode(String inStr) throws UnsupportedEncodingException {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
