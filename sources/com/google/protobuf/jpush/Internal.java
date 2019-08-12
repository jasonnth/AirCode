package com.google.protobuf.jpush;

import java.io.UnsupportedEncodingException;
import org.spongycastle.i18n.LocalizedMessage;
import p005cn.jpush.android.JPushConstants;

public class Internal {

    public interface EnumLite {
        int getNumber();
    }

    public interface EnumLiteMap<T extends EnumLite> {
        T findValueByNumber(int i);
    }

    public static String stringDefaultValue(String bytes) {
        try {
            return new String(bytes.getBytes(LocalizedMessage.DEFAULT_ENCODING), JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static ByteString bytesDefaultValue(String bytes) {
        try {
            return ByteString.copyFrom(bytes.getBytes(LocalizedMessage.DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static boolean isValidUtf8(ByteString byteString) {
        int size = byteString.size();
        int index = 0;
        while (index < size) {
            int index2 = index + 1;
            int byte1 = byteString.byteAt(index) & 255;
            if (byte1 < 128) {
                index = index2;
            } else if (byte1 < 194 || byte1 > 244 || index2 >= size) {
                return false;
            } else {
                index = index2 + 1;
                int byte2 = byteString.byteAt(index2) & 255;
                if (byte2 < 128 || byte2 > 191) {
                    int i = index;
                    return false;
                } else if (byte1 <= 223) {
                    continue;
                } else if (index >= size) {
                    int i2 = index;
                    return false;
                } else {
                    int index3 = index + 1;
                    int byte3 = byteString.byteAt(index) & 255;
                    if (byte3 < 128 || byte3 > 191) {
                        return false;
                    }
                    if (byte1 <= 239) {
                        if (byte1 == 224 && byte2 < 160) {
                            return false;
                        }
                        if (byte1 == 237 && byte2 > 159) {
                            return false;
                        }
                    } else if (index3 >= size) {
                        return false;
                    } else {
                        int index4 = index3 + 1;
                        int byte4 = byteString.byteAt(index3) & 255;
                        if (byte4 < 128 || byte4 > 191) {
                            int i3 = index4;
                            return false;
                        } else if ((byte1 != 240 || byte2 >= 144) && (byte1 != 244 || byte2 <= 143)) {
                            index3 = index4;
                        } else {
                            int i4 = index4;
                            return false;
                        }
                    }
                    index = index3;
                }
            }
        }
        int i5 = index;
        return true;
    }
}
