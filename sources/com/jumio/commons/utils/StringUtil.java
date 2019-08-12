package com.jumio.commons.utils;

import java.util.regex.Pattern;

public class StringUtil {
    public static String trim(String value, int maxLength) {
        if (value == null) {
            return value;
        }
        String value2 = value.trim();
        if (value2.length() > maxLength) {
            return value2.substring(0, maxLength);
        }
        return value2;
    }

    public static String trim(String value, int maxLength, String regex) {
        if (value == null || regex == null) {
            return value;
        }
        trim(value, maxLength);
        if (!Pattern.compile(regex).matcher(value).matches()) {
            return null;
        }
        return value;
    }

    public static boolean nullOrEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static String binToHex(byte[] bin) {
        String hex = new String("");
        for (byte valueOf : bin) {
            hex = hex.concat(String.format("%02X", new Object[]{Byte.valueOf(valueOf)}));
        }
        return hex;
    }

    public static byte[] hexToBin(String hex) {
        byte[] bin = new byte[(hex.length() / 2)];
        for (int i = 0; i < bin.length; i++) {
            bin[i] = (byte) ((Character.digit(hex.charAt(i * 2), 16) << 4) + Character.digit(hex.charAt((i * 2) + 1), 16));
        }
        return bin;
    }
}
