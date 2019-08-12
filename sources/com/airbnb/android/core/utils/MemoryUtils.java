package com.airbnb.android.core.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.SharedPreferences;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.Strap;

public class MemoryUtils {
    private static final String OOM_OCCURRED = "memory_utils_oom_occurred";
    private static final String OOM_TIME = "memory_utils_oom_time";
    private final AirbnbPreferences mPreferences;

    public MemoryUtils(AirbnbPreferences preferences) {
        this.mPreferences = preferences;
    }

    @TargetApi(19)
    public static boolean isLowMemoryDevice() {
        boolean isLowMemory = false;
        ActivityManager activityManager = (ActivityManager) CoreApplication.appContext().getSystemService("activity");
        if (AndroidVersion.isAtLeastKitKat()) {
            isLowMemory = activityManager.isLowRamDevice();
        }
        return activityManager.getMemoryClass() <= 48 || isLowMemory;
    }

    public void handleCaughtOOM(String action) {
        BugsnagWrapper.notify((Throwable) new OutOfMemoryError(action));
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv(AirbnbConstants.OOM_CRASH_CAUGHT, "true").mo11639kv("action", action), true);
        markOOMOccurred(true);
    }

    public void markOOMOccurred(boolean occurred) {
        this.mPreferences.getGlobalSharedPreferences().edit().putBoolean(OOM_OCCURRED, occurred).putLong(OOM_TIME, System.currentTimeMillis()).apply();
    }

    public boolean isLowMemoryDeviceOrOomOccurredInLastWeekProd() {
        return BuildHelper.isReleaseBuild() && (isLowMemoryDevice() || hasOomOccurredInLastWeek());
    }

    public boolean hasOomOccurredInLastWeek() {
        SharedPreferences prefs = this.mPreferences.getGlobalSharedPreferences();
        if (!prefs.getBoolean(OOM_OCCURRED, false) || System.currentTimeMillis() - prefs.getLong(OOM_TIME, 0) >= 604800000) {
            return false;
        }
        return true;
    }

    public static int getMemoryClass() {
        return ((ActivityManager) CoreApplication.appContext().getSystemService("activity")).getMemoryClass();
    }

    public static int getLastTrimLevel() {
        RunningAppProcessInfo procInfo = new RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(procInfo);
        return procInfo.lastTrimLevel;
    }
}
