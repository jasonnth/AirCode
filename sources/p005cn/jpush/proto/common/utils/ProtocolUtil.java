package p005cn.jpush.proto.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import p005cn.jpush.android.util.ByteBufferUtils;
import p005cn.jpush.proto.common.commands.JResponse;

/* renamed from: cn.jpush.proto.common.utils.ProtocolUtil */
public class ProtocolUtil {
    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String TAG = "ProtocolUtil";

    public static byte[] getBytesConsumed(ByteBuffer buffer) {
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }

    public static byte[] getBytes(ByteBuffer buffer) {
        byte[] bytes = new byte[buffer.remaining()];
        buffer.asReadOnlyBuffer().flip();
        buffer.get(bytes);
        return bytes;
    }

    public static byte[] tlv2ToByteArray(String value) {
        if (value == null || "".equals(value)) {
            return new byte[]{0, 0};
        }
        byte[] valueBytes = null;
        try {
            valueBytes = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        short len = (short) valueBytes.length;
        byte[] result = new byte[(len + 2)];
        System.arraycopy(short2ByteArray(len), 0, result, 0, 2);
        System.arraycopy(valueBytes, 0, result, 2, len);
        return result;
    }

    public static byte[] fixedStringToBytes(String value, int length) {
        if (value == null || "".equals(value)) {
            return new byte[]{0, 0, 0, 0};
        }
        byte[] valueBytes = null;
        try {
            valueBytes = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        byte[] results = getDefaultByte(length);
        System.arraycopy(valueBytes, 0, results, 0, valueBytes.length > length ? length : valueBytes.length);
        return results;
    }

    public static void fillIntData(byte[] data, int intValue, int pos) {
        System.arraycopy(int2ByteArray(intValue), 0, data, pos, 4);
    }

    public static void fillStringData(byte[] data, String str, int pos) {
        byte[] tmp = str.getBytes();
        System.arraycopy(tmp, 0, data, pos, tmp.length);
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.toHexLog(fixedStringToBytes("ab", 4)));
    }

    public static byte[] getDefaultByte(int byteSize) {
        byte[] data = new byte[byteSize];
        for (int i = 0; i < data.length; i++) {
            data[0] = 0;
        }
        return data;
    }

    public static String getTlv2(ByteBuffer buffer, JResponse jResponse) {
        byte[] bytes = new byte[ByteBufferUtils.getShort(buffer, jResponse)];
        ByteBufferUtils.get(buffer, bytes, jResponse);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getTlv2(ByteBuffer buffer) {
        byte[] bytes = new byte[buffer.getShort()];
        buffer.get(bytes);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getString(ByteBuffer buffer, int length) {
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte[] short2ByteArray(short value) {
        return new byte[]{(byte) (value >>> 8), (byte) value};
    }

    public static byte[] int2ByteArray(int value) {
        return new byte[]{(byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value};
    }

    public static byte[] long2ByteArray(long value) {
        return new byte[]{(byte) ((int) (value >>> 56)), (byte) ((int) (value >>> 48)), (byte) ((int) (value >>> 40)), (byte) ((int) (value >>> 32)), (byte) ((int) (value >>> 24)), (byte) ((int) (value >>> 16)), (byte) ((int) (value >>> 8)), (byte) ((int) value)};
    }
}
