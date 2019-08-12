package com.kount.api;

import java.security.MessageDigest;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2ParameterSpec;
import p005cn.jpush.android.JPushConstants;

class HashUtils {
    public static String convertToSha256Hash(String rawString) {
        try {
            byte[] array = MessageDigest.getInstance(McElieceCCA2ParameterSpec.DEFAULT_MD).digest(rawString.getBytes(JPushConstants.ENCODING_UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : array) {
                sb.append(Integer.toHexString((aByte & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
