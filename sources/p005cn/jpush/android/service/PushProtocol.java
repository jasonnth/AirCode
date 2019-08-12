package p005cn.jpush.android.service;

import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.PushProtocol */
public class PushProtocol {
    private static final String soName = "jpush210";

    public static native synchronized int Close(long j);

    public static native int GetSdkVersion();

    public static native int HbJPush(long j, long j2, int i, long j3, short s);

    public static native int IMProtocol(long j, byte[] bArr, int i);

    public static native long InitConn();

    public static native int InitPush(long j, String str, int i);

    public static native int LogPush(long j, long j2, byte[] bArr, long j3, String str, String str2, long j4, short s);

    public static native int MsgResponse(long j, int i, long j2, byte b, long j3, long j4, int i2);

    public static native int RecvPush(long j, byte[] bArr, int i);

    public static native int RegPush(long j, long j2, String str, String str2, String str3, String str4);

    public static native int TagAlias(long j, long j2, int i, long j3, String str, String str2);

    static {
        try {
            System.loadLibrary(soName);
        } catch (Throwable e) {
            Logger.m1420e("PushProtocol", "System.loadLibrary::jpush210" + e);
            e.printStackTrace();
        }
    }
}
