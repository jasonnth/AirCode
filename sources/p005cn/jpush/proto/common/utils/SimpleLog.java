package p005cn.jpush.proto.common.utils;

/* renamed from: cn.jpush.proto.common.utils.SimpleLog */
public class SimpleLog {
    private static final String TAG = "[JPush] - ";

    public static void debug(String msg) {
    }

    public static void info(String msg) {
        System.out.println(TAG + msg);
    }

    public static void warn(String msg) {
        System.out.println(TAG + msg);
    }

    public static void error(String msg) {
        System.out.println(TAG + msg);
    }
}
