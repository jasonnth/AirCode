package p005cn.jpush.android.util;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import p005cn.jpush.android.api.JPushCrashHandler;
import p005cn.jpush.proto.common.commands.JResponse;

/* renamed from: cn.jpush.android.util.ByteBufferUtils */
public class ByteBufferUtils {
    public static final int ERROR_CODE = 10000;
    private static final String TAG = "ByteBufferUtils";

    public static int getInt(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getInt();
        } catch (BufferUnderflowException e) {
            onException(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            onException(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            onException(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = 10000;
        }
        return -1;
    }

    public static short getShort(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getShort();
        } catch (BufferUnderflowException e) {
            onException(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            onException(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            onException(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = 10000;
        }
        return -1;
    }

    public static ByteBuffer get(ByteBuffer byteBuffer, byte[] bytes, JResponse jResponse) {
        try {
            return byteBuffer.get(bytes);
        } catch (BufferUnderflowException e) {
            onException(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            onException(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            onException(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = 10000;
        }
        return null;
    }

    public static Byte get(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return Byte.valueOf(byteBuffer.get());
        } catch (BufferUnderflowException e) {
            onException(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            onException(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            onException(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = 10000;
        }
        return null;
    }

    public static long getLong(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getLong();
        } catch (BufferUnderflowException e) {
            onException(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            onException(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            onException(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = 10000;
        }
        return 0;
    }

    private static void onException(Throwable throwable, JResponse jResponse, ByteBuffer byteBuffer) {
        JPushCrashHandler.getInstance().handleException(throwable, generalExtraStr(throwable, jResponse, byteBuffer));
    }

    private static String generalExtraStr(Throwable throwable, JResponse jResponse, ByteBuffer byteBuffer) {
        StringBuilder stringBuilder = new StringBuilder();
        if (jResponse != null) {
            stringBuilder.append(jResponse == null ? "jResponse is null" : jResponse.toString());
            stringBuilder.append("|bytebuffer:" + (byteBuffer == null ? "byteBuffer is null" : byteBuffer.toString()));
        }
        Logger.m1422ee(TAG, "byteBuffer info:" + stringBuilder.toString());
        Logger.m1422ee(TAG, "parse data error stackTrace:" + throwable.getStackTrace().toString());
        return stringBuilder.toString();
    }
}
