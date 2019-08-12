package net.p318sf.scuba.util;

import com.facebook.appevents.AppEventsConstants;

/* renamed from: net.sf.scuba.util.Hex */
public final class Hex {
    private static final String HEXCHARS = "0123456789abcdefABCDEF";
    private static final boolean LEFT = true;
    private static final String PRINTABLE = " .,:;'`\"<>()[]{}?/\\!@#$%^&*_-=+|~0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final boolean RIGHT = false;

    private Hex() {
    }

    public static String byteToHexString(byte b) {
        byte b2 = b & 255;
        return ((b2 < 16 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + Integer.toHexString(b2)).toUpperCase();
    }

    public static String bytesToASCIIString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            char c = (char) b;
            if (PRINTABLE.indexOf(c) < 0) {
                c = '.';
            }
            stringBuffer.append(Character.toString(c));
        }
        return stringBuffer.toString();
    }

    static String[] bytesToASCIIStrings(byte[] bArr, int i, int i2) {
        byte[][] split = split(bArr, i);
        String[] strArr = new String[split.length];
        for (int i3 = 0; i3 < split.length; i3++) {
            strArr[i3] = bytesToASCIIString(split[i3]);
        }
        return strArr;
    }

    public static String bytesToHexString(byte[] bArr) {
        return bytesToHexString(bArr, 1000);
    }

    public static String bytesToHexString(byte[] bArr, int i) {
        return bArr == null ? "NULL" : bytesToHexString(bArr, 0, bArr.length, i);
    }

    public static String bytesToHexString(byte[] bArr, int i, int i2) {
        return bytesToHexString(bArr, i, i2, 1000);
    }

    public static String bytesToHexString(byte[] bArr, int i, int i2, int i3) {
        if (bArr == null) {
            return "NULL";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 != 0 && i4 % i3 == 0) {
                stringBuffer.append("\n");
            }
            stringBuffer.append(byteToHexString(bArr[i + i4]));
        }
        return stringBuffer.toString();
    }

    public static String bytesToPrettyString(byte[] bArr) {
        return bytesToPrettyString(bArr, 16, LEFT, 4, null, LEFT);
    }

    public static String bytesToPrettyString(byte[] bArr, int i, boolean z, int i2, String str, boolean z2) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] bytesToSpacedHexStrings = bytesToSpacedHexStrings(bArr, i, i * 3);
        String[] bytesToASCIIStrings = bytesToASCIIStrings(bArr, i, i);
        int i3 = 0;
        while (i3 < bytesToSpacedHexStrings.length) {
            if (z) {
                stringBuffer.append(pad(Integer.toHexString(i3 * i).toUpperCase(), i2, '0', LEFT) + ": ");
            } else {
                stringBuffer.append(pad(i3 == 0 ? str : "", i2, ' ', LEFT) + " ");
            }
            stringBuffer.append(bytesToSpacedHexStrings[i3]);
            if (z2) {
                stringBuffer.append(" " + bytesToASCIIStrings[i3]);
            }
            stringBuffer.append("\n");
            i3++;
        }
        return stringBuffer.toString();
    }

    public static String bytesToSpacedHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < bArr.length) {
            stringBuffer.append(byteToHexString(bArr[i]));
            stringBuffer.append(i < bArr.length + -1 ? " " : "");
            i++;
        }
        return stringBuffer.toString().toUpperCase();
    }

    private static String[] bytesToSpacedHexStrings(byte[] bArr, int i, int i2) {
        byte[][] split = split(bArr, i);
        String[] strArr = new String[split.length];
        for (int i3 = 0; i3 < split.length; i3++) {
            strArr[i3] = bytesToSpacedHexString(split[i3]);
            strArr[i3] = pad(strArr[i3], i2, ' ', false);
        }
        return strArr;
    }

    static int hexDigitToInt(char c) throws NumberFormatException {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
            case 'a':
                return 10;
            case 'B':
            case 'b':
                return 11;
            case 'C':
            case 'c':
                return 12;
            case 'D':
            case 'd':
                return 13;
            case 'E':
            case 'e':
                return 14;
            case 'F':
            case 'f':
                return 15;
            default:
                throw new NumberFormatException();
        }
    }

    public static byte hexStringToByte(String str) throws NumberFormatException {
        byte[] hexStringToBytes = hexStringToBytes(str);
        if (hexStringToBytes.length == 1) {
            return hexStringToBytes[0];
        }
        throw new NumberFormatException();
    }

    public static byte[] hexStringToBytes(String str) throws NumberFormatException {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!Character.isWhitespace(charAt)) {
                if (HEXCHARS.indexOf(charAt) < 0) {
                    throw new NumberFormatException();
                }
                stringBuffer.append(charAt);
            }
        }
        if (stringBuffer.length() % 2 != 0) {
            stringBuffer.insert(0, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        byte[] bArr = new byte[(stringBuffer.length() / 2)];
        for (int i2 = 0; i2 < stringBuffer.length(); i2 += 2) {
            bArr[i2 / 2] = (byte) (((hexDigitToInt(stringBuffer.charAt(i2)) & 255) << 4) | (hexDigitToInt(stringBuffer.charAt(i2 + 1)) & 255));
        }
        return bArr;
    }

    public static int hexStringToInt(String str) throws NumberFormatException {
        byte[] hexStringToBytes = hexStringToBytes(str);
        if (hexStringToBytes.length != 4) {
            throw new NumberFormatException();
        }
        return (hexStringToBytes[3] & 255) | ((hexStringToBytes[0] & 255) << 24) | ((hexStringToBytes[1] & 255) << 16) | ((hexStringToBytes[2] & 255) << 8);
    }

    public static short hexStringToShort(String str) throws NumberFormatException {
        byte[] hexStringToBytes = hexStringToBytes(str);
        if (hexStringToBytes.length != 2) {
            throw new NumberFormatException();
        }
        return (short) ((hexStringToBytes[1] & 255) | ((hexStringToBytes[0] & 255) << 8));
    }

    public static String intToHexString(int i) {
        return ((i < 268435456 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + (i < 16777216 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + (i < 1048576 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + (i < 65536 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + (i < 4096 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + (i < 256 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + (i < 16 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + Integer.toHexString(i)).toUpperCase();
    }

    private static String pad(String str, int i, char c, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        if (length >= i) {
            return str;
        }
        int i2 = i - length;
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append(c);
        }
        return z ? stringBuffer.toString() + str : str + stringBuffer.toString();
    }

    public static String shortToHexString(short s) {
        short s2 = s & 65535;
        String str = (s2 < 4096 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + (s2 < 256 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + (s2 < 16 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + Integer.toHexString(s);
        if (str.length() > 4) {
            str = str.substring(str.length() - 4, str.length());
        }
        return str.toUpperCase();
    }

    public static byte[][] split(byte[] bArr, int i) {
        int length = bArr.length / i;
        int length2 = bArr.length % i;
        byte[][] bArr2 = new byte[((length2 > 0 ? 1 : 0) + length)][];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            bArr2[i3] = new byte[i];
            System.arraycopy(bArr, i2, bArr2[i3], 0, i);
            i2 += i;
        }
        if (length2 > 0) {
            bArr2[length] = new byte[length2];
            System.arraycopy(bArr, i2, bArr2[length], 0, length2);
        }
        return bArr2;
    }

    public static String toHexString(byte[] bArr) {
        return bytesToHexString(bArr, 0, bArr.length, 1000);
    }

    public static String toHexString(byte[] bArr, int i) {
        return bytesToHexString(bArr, 0, bArr.length, i);
    }
}
