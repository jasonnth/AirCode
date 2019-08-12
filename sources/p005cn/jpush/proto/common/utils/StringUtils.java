package p005cn.jpush.proto.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;

/* renamed from: cn.jpush.proto.common.utils.StringUtils */
public class StringUtils {
    private static final String HEX = "0123456789ABCDEF";

    public static boolean equals(String s1, String s2) {
        return (s1 == null && s2 == null) || (s1 != null && s1.equals(s2));
    }

    public static String emptyStringIfNull(String s) {
        return s == null ? "" : s;
    }

    public static String toArrayString(String... serverUrl) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String url : serverUrl) {
            sb.append(url).append(", ");
        }
        sb.append("]");
        String s = sb.toString();
        sb.setLength(0);
        return s;
    }

    public static String humanReadableByteCount(long bytes) {
        if (bytes < ((long) 1024)) {
            return bytes + " B";
        }
        int exp = (int) (Math.log((double) bytes) / Math.log((double) 1024));
        return String.format("%.1f %sB", new Object[]{Double.valueOf(((double) bytes) / Math.pow((double) 1024, (double) exp)), "KMGTPE".charAt(exp - 1) + ""});
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

    public static String valueOfInt(int i, int length) {
        return valueOfString(String.valueOf(i), length);
    }

    public static String valueOfLong(long i, int length) {
        return valueOfString(String.valueOf(i), length);
    }

    public static String valueOfString(String i, int length) {
        int size = i.length();
        if (size >= length) {
            return i;
        }
        char[] zeros = new char[(length - size)];
        Arrays.fill(zeros, '0');
        StringBuilder buffer = new StringBuilder();
        buffer.append(zeros);
        buffer.append(i);
        String s = buffer.toString();
        buffer.setLength(0);
        return s;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isTrimedEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.length() > 0;
    }

    public static String list2String(Collection<Long> list) {
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o.toString() + ",");
        }
        String string = sb.toString().trim();
        if (list.size() > 0) {
            String string2 = string.substring(0, string.length() - 1);
        }
        String s = sb.toString();
        sb.setLength(0);
        return s;
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

    public static String toMD5(InputStream input, int size) {
        String str = null;
        if (input == null || size == 0) {
            return str;
        }
        try {
            byte[] buffer = new byte[size];
            input.read(buffer);
            return toMD5(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String toMD5(byte[] data) {
        String str = null;
        if (data == null || data.length == 0) {
            return str;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data);
            return toHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return result;
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

    public static String toHexLog(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(buf.length * 2);
        for (byte appendHex : buf) {
            appendHex(result, appendHex);
            result.append(' ');
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 15)).append(HEX.charAt(b & 15));
    }
}
