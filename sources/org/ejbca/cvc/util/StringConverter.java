package org.ejbca.cvc.util;

public final class StringConverter {
    private static final char[] HEXCHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String HEXINDEX = "0123456789abcdef          ABCDEF";

    private StringConverter() {
    }

    public static byte[] hexToByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            int indexOf = (HEXINDEX.indexOf(str.charAt(i)) & 15) << 4;
            i = i3 + 1;
            bArr[i2] = (byte) ((HEXINDEX.indexOf(str.charAt(i3)) & 15) + indexOf);
        }
        return bArr;
    }

    public static String byteToHex(byte[] bArr) {
        return byteToHex(bArr, null);
    }

    public static String byteToHex(byte[] bArr, String str) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(byteToHex(bArr[i]));
            if (str != null && i + 1 < length) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    public static String byteToHex(byte b) {
        byte b2 = b & 255;
        char c = HEXCHAR[(b2 >> 4) & 15];
        return Character.toString(c) + HEXCHAR[b2 & 15];
    }
}
