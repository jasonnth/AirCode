package p005cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: cn.jpush.android.util.SpfHelper */
public class SpfHelper {
    private static SpfHelper helper;
    private final String Spf_Name = "JPushSA_Config";
    private SharedPreferences spfInstance = null;

    public static SpfHelper getHelper() {
        if (helper == null) {
            helper = new SpfHelper();
        }
        return helper;
    }

    private SharedPreferences getSpf(Context ctx) {
        if (this.spfInstance == null) {
            synchronized (SpfHelper.class) {
                this.spfInstance = ctx.getSharedPreferences("JPushSA_Config", 0);
            }
        }
        return this.spfInstance;
    }

    public String getString(Context ctx, String key, String defValue) {
        return getSpf(ctx).getString(key, defValue);
    }

    public void putString(Context ctx, String key, String value) {
        getSpf(ctx).edit().putString(key, value).commit();
    }

    public long getLong(Context ctx, String key, long defValue) {
        return getSpf(ctx).getLong(key, defValue);
    }

    public void putLong(Context ctx, String key, long value) {
        getSpf(ctx).edit().putLong(key, value).commit();
    }
}
