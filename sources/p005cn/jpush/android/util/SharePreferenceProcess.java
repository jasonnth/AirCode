package p005cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import p005cn.jpush.android.JPush;

/* renamed from: cn.jpush.android.util.SharePreferenceProcess */
public class SharePreferenceProcess {
    private static final String SP_FILE = "cn.jpush.android.user.profile";
    private static final int SP_MODE = 4;
    private static final String TAG = "SharePreferenceProcess";
    private static final String WARING_CODE = "unexpected! context is null";
    private static SharedPreferences sharedPreferences = null;

    private static boolean checkContext(Context context) {
        if (context != null) {
            return true;
        }
        Logger.m1434ww(TAG, "context is null, in SharePreferenceProcess.init");
        return false;
    }

    private static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(SP_FILE, 4);
        } else if (JPush.mRemoteService == null && !JPush.runInPushProcess) {
            sharedPreferences = context.getSharedPreferences(SP_FILE, 4);
        }
    }

    public static void commitString(Context context, String key, String value) {
        if (checkContext(context)) {
            init(context);
            Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
            return;
        }
        Logger.m1432w(TAG, WARING_CODE);
    }

    public static String getString(Context context, String key, String defaultValue) {
        if (checkContext(context)) {
            init(context);
            return sharedPreferences.getString(key, defaultValue);
        }
        Logger.m1432w(TAG, WARING_CODE);
        return defaultValue;
    }

    public static void commitLong(Context context, String key, long value) {
        if (checkContext(context)) {
            init(context);
            Editor editor = sharedPreferences.edit();
            editor.putLong(key, value);
            editor.apply();
            return;
        }
        Logger.m1432w(TAG, WARING_CODE);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        if (checkContext(context)) {
            init(context);
            return sharedPreferences.getLong(key, defaultValue);
        }
        Logger.m1432w(TAG, WARING_CODE);
        return defaultValue;
    }

    public static void commitInt(Context context, String key, int value) {
        if (checkContext(context)) {
            init(context);
            Editor editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
            return;
        }
        Logger.m1432w(TAG, WARING_CODE);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        if (checkContext(context)) {
            init(context);
            return sharedPreferences.getInt(key, defaultValue);
        }
        Logger.m1432w(TAG, WARING_CODE);
        return defaultValue;
    }

    public static void commitBoolean(Context context, String key, boolean value) {
        if (checkContext(context)) {
            init(context);
            Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
            return;
        }
        Logger.m1432w(TAG, WARING_CODE);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        if (checkContext(context)) {
            init(context);
            return sharedPreferences.getBoolean(key, defaultValue);
        }
        Logger.m1432w(TAG, WARING_CODE);
        return defaultValue;
    }

    public static void removeKey(Context context, String key) {
        if (checkContext(context)) {
            init(context);
            Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
            return;
        }
        Logger.m1432w(TAG, WARING_CODE);
    }
}
