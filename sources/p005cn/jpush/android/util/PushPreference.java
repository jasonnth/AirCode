package p005cn.jpush.android.util;

import p005cn.jpush.android.JPush;

/* renamed from: cn.jpush.android.util.PushPreference */
public class PushPreference extends BasePreferenceManager {
    private static final String CORE_JPUSH_PACKAGE = "CORE_JPUSH_PACKAGE";
    private static final String NETWORK_STATUS = "network_status";
    private static final String SAVED_CUSTOM_BUILDER = "jpush_save_custom_builder";
    private static final String TODAY = "today_date";

    public static void setCustomBuilder(String paramkey, String value) {
        if (JPush.mApplicationContext != null) {
            commitString(SAVED_CUSTOM_BUILDER + paramkey, value);
        }
    }

    public static String getCustomBuilder(String paramKey) {
        return getString(SAVED_CUSTOM_BUILDER + paramKey, "");
    }

    public static String getCoreJPushPackage() {
        return getString(CORE_JPUSH_PACKAGE, null);
    }

    public static void setCoreJPushPackage(String corePackage) {
        commitString(CORE_JPUSH_PACKAGE, corePackage);
    }

    public static synchronized long getAndSetToday() {
        long before;
        synchronized (PushPreference.class) {
            before = getLong(TODAY, System.currentTimeMillis());
            commitLong(TODAY, System.currentTimeMillis());
        }
        return before;
    }

    public static String getNetworkStatus() {
        return getString(NETWORK_STATUS, null);
    }

    public static void setNetworkStatus(String statusString) {
        commitString(NETWORK_STATUS, statusString);
    }
}
