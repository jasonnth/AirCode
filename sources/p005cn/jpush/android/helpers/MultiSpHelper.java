package p005cn.jpush.android.helpers;

import android.content.Context;
import android.os.RemoteException;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.SharePreferenceProcess;

/* renamed from: cn.jpush.android.helpers.MultiSpHelper */
public class MultiSpHelper {
    private static final String NULL_WARNING = "mRemoteService is null, bindService has not been connected";
    private static final String REMOTE_WARNING = "unexpected! aidl has wrong with RemoteException";
    private static final String TAG = "MultiSpHelper";

    public static void commitString(Context context, String key, String value) {
        try {
            JPush.mRemoteService.commitString(key, value);
        } catch (RemoteException e) {
            Logger.m1432w(TAG, REMOTE_WARNING);
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, NULL_WARNING);
            SharePreferenceProcess.commitString(context, key, value);
        }
    }

    public static String getString(Context context, String key, String defaultValue) {
        try {
            return JPush.mRemoteService.getString(key, defaultValue);
        } catch (RemoteException e) {
            Logger.m1432w(TAG, REMOTE_WARNING);
            return defaultValue;
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, NULL_WARNING);
            return SharePreferenceProcess.getString(context, key, defaultValue);
        }
    }

    public static void commitLong(Context context, String key, long value) {
        try {
            JPush.mRemoteService.commitLong(key, value);
        } catch (RemoteException e) {
            Logger.m1432w(TAG, REMOTE_WARNING);
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, NULL_WARNING);
            SharePreferenceProcess.commitLong(context, key, value);
        }
    }

    public static long getLong(Context context, String key, long defaultValue) {
        try {
            return JPush.mRemoteService.getLong(key, defaultValue);
        } catch (RemoteException e) {
            Logger.m1432w(TAG, REMOTE_WARNING);
            return defaultValue;
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, NULL_WARNING);
            return SharePreferenceProcess.getLong(context, key, defaultValue);
        }
    }

    public static void commitInt(Context context, String key, int value) {
        try {
            JPush.mRemoteService.commitInt(key, value);
        } catch (RemoteException e) {
            Logger.m1432w(TAG, REMOTE_WARNING);
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, NULL_WARNING);
            SharePreferenceProcess.commitInt(context, key, value);
        }
    }

    public static int getInt(Context context, String key, int defaultValue) {
        try {
            return JPush.mRemoteService.getInt(key, defaultValue);
        } catch (RemoteException e) {
            Logger.m1432w(TAG, REMOTE_WARNING);
            return defaultValue;
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, NULL_WARNING);
            return SharePreferenceProcess.getInt(context, key, defaultValue);
        }
    }

    public static void commitBoolean(Context context, String key, boolean value) {
        try {
            JPush.mRemoteService.commitBoolean(key, value);
        } catch (RemoteException e) {
            Logger.m1432w(TAG, REMOTE_WARNING);
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, NULL_WARNING);
            SharePreferenceProcess.commitBoolean(context, key, value);
        }
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        try {
            return JPush.mRemoteService.getBoolean(key, defaultValue);
        } catch (RemoteException e) {
            Logger.m1432w(TAG, REMOTE_WARNING);
            return defaultValue;
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, NULL_WARNING);
            return SharePreferenceProcess.getBoolean(context, key, defaultValue);
        }
    }
}
