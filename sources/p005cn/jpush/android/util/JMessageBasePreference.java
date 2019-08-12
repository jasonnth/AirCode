package p005cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Random;

/* renamed from: cn.jpush.android.util.JMessageBasePreference */
public class JMessageBasePreference {
    private static final String AES_ENCRYPTION_SEED = "jmessage";
    private static final String JMESSAGE_PREF = "cn.jmessage.preferences";
    private static final String KEY_IM_NEXT_RID = "im_next_rid";
    private static SharedPreferences mSharedPreferences = null;

    public static void init(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(JMESSAGE_PREF, 0);
        }
    }

    public static void removeKey(String key) {
        Editor editor = mSharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void removeAll() {
        Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    protected static void commitString(String key, String value) {
        Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    protected static String getString(String key, String failValue) {
        return mSharedPreferences.getString(key, failValue);
    }

    protected static void commitStringWithEncryption(String key, String value) {
        Editor editor = mSharedPreferences.edit();
        editor.putString(key, getEncryptedString(value));
        editor.apply();
    }

    protected static String getStringUnencrypted(String key, String failValue) {
        try {
            return AESEncryptor.decrypt(AES_ENCRYPTION_SEED, mSharedPreferences.getString(key, failValue));
        } catch (Exception e) {
            Logger.m1432w("", "Unexpected - failed to AES decrypt.");
            return failValue;
        }
    }

    protected static void commitInt(String key, int value) {
        Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    protected static int getInt(String key, int failValue) {
        return mSharedPreferences.getInt(key, failValue);
    }

    protected static void commitLong(String key, long value) {
        Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    protected static long getLong(String key, long failValue) {
        return mSharedPreferences.getLong(key, failValue);
    }

    protected static void commitBoolean(String key, boolean value) {
        Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    protected static Boolean getBoolean(String key, boolean failValue) {
        return Boolean.valueOf(mSharedPreferences.getBoolean(key, failValue));
    }

    protected static void commitString(Context context, String key, String value) {
        init(context);
        Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    protected static String getString(Context context, String key, String faillValue) {
        init(context);
        return mSharedPreferences.getString(key, faillValue);
    }

    protected static void commitInt(Context context, String key, int value) {
        init(context);
        Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    protected static int getInt(Context context, String key, int failValue) {
        init(context);
        return mSharedPreferences.getInt(key, failValue);
    }

    protected static void commitLong(Context context, String key, long value) {
        init(context);
        Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    protected static long getLong(Context context, String key, long failValue) {
        init(context);
        return mSharedPreferences.getLong(key, failValue);
    }

    protected static void commitBoolean(Context context, String key, boolean value) {
        init(context);
        Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    protected static Boolean getBoolean(Context context, String key, boolean failValue) {
        init(context);
        return Boolean.valueOf(mSharedPreferences.getBoolean(key, failValue));
    }

    private static String getEncryptedString(String value) {
        String encrypted = value;
        try {
            return AESEncryptor.encrypt(AES_ENCRYPTION_SEED, value);
        } catch (Exception e) {
            Logger.m1432w("", "Unexpected - failed to AES encrypt.");
            return encrypted;
        }
    }

    public static synchronized long getNextRid() {
        long j;
        synchronized (JMessageBasePreference.class) {
            long oldRid = getLong(KEY_IM_NEXT_RID, getStartRid()) % 32767;
            commitLong(KEY_IM_NEXT_RID, oldRid + 2);
            j = oldRid + 2;
        }
        return j;
    }

    private static long getStartRid() {
        long rid = (long) Math.abs(new Random().nextInt(32767));
        if (rid % 2 == 0) {
            return rid;
        }
        return rid + 1;
    }
}
