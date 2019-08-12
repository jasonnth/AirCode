package p005cn.jpush.android.util;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/* renamed from: cn.jpush.android.util.StringUtils */
public class StringUtils {
    private static final String HEX = "0123456789ABCDEF";
    private static String hexString = HEX;

    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0 || s.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean equals(String paramString1, String paramString2) {
        if (paramString1 == null || paramString2 == null) {
            return false;
        }
        return paramString1.equals(paramString2);
    }

    public static String encode(String str) {
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 240) >> 4));
            sb.append(hexString.charAt((bytes[i] & 15) >> 0));
        }
        return sb.toString();
    }

    public static String decode(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        for (int i = 0; i < bytes.length(); i += 2) {
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4) | hexString.indexOf(bytes.charAt(i + 1)));
        }
        return new String(baos.toByteArray());
    }

    public static void main(String[] args) {
    }

    public static String toMD5(String source) {
        String str = null;
        if (source == null || "".equals(source)) {
            return str;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source.getBytes());
            return toHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    public static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(buf.length * 2);
        for (byte appendHex : buf) {
            appendHex(result, appendHex);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 15)).append(HEX.charAt(b & 15));
    }

    public static String fixedLengthString(String s, int expectedLength) {
        int l = s.length();
        if (l < expectedLength) {
            for (int i = 0; i < expectedLength - l; i++) {
                s = s + " ";
            }
        }
        return s;
    }

    public static boolean isIPAddress(String ipaddr) {
        return Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b").matcher(ipaddr).matches();
    }

    public static String filterSpecialCharacter(String s) {
        if (isEmpty(s)) {
            return "";
        }
        return Pattern.compile("[^\\w#$@\\-一-龥]+").matcher(s).replaceAll("");
    }
}
