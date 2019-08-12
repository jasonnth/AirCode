package com.airbnb.android.core.analytics;

import android.content.SharedPreferences;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.modules.DeferredAppInitExecutor;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;

public class AppLaunchAnalytics extends BaseAnalytics {
    public static final String COLD_START = "cold_start";
    private static final String COLD_START_FIRST_ACTIVITY_SHOWN = "cold_start_first_activity_shown";
    private static final String COLD_START_FIRST_LOADED_ACTIVITY = "cold_start_first_activity";
    private static final String KEY_PRELOAD_APP_BOY = "preload_app_boy";
    private static final String KEY_PRELOAD_JODA_TIME = "preload_joda_time";
    private static final String KEY_PROLOAD_SHARED_PREFERENCES = "preload_shared_prefs";
    public static final String PREFS_FIRST_LAUNCH = "first_launch";
    private final AirbnbPreferences airbnbPreferences;
    private boolean hasLoggedFirstActivityLoaded;
    private boolean hasLoggedFirstActivityShown;
    private final PerformanceLogger performanceLogger;

    public AppLaunchAnalytics(PerformanceLogger performanceLogger2, AirbnbPreferences airbnbPreferences2) {
        this.performanceLogger = performanceLogger2;
        this.airbnbPreferences = airbnbPreferences2;
    }

    public void trackColdLaunchStart(long startTimeStampMilliseconds) {
        this.performanceLogger.markStart(COLD_START, null, Long.valueOf(startTimeStampMilliseconds));
        this.performanceLogger.markStart(COLD_START_FIRST_LOADED_ACTIVITY, null, Long.valueOf(startTimeStampMilliseconds));
        this.performanceLogger.markStart(COLD_START_FIRST_ACTIVITY_SHOWN, null, Long.valueOf(startTimeStampMilliseconds));
    }

    public void trackColdLaunchEnd(long stopTimeStampMilliseconds) {
        SharedPreferences sharedPreferences = this.airbnbPreferences.getSharedPreferences();
        boolean isFirstLaunch = sharedPreferences.getBoolean(PREFS_FIRST_LAUNCH, true);
        sharedPreferences.edit().putBoolean(PREFS_FIRST_LAUNCH, false).apply();
        this.performanceLogger.markCompleted(COLD_START, Strap.make().mo11640kv(PREFS_FIRST_LAUNCH, isFirstLaunch), Long.valueOf(stopTimeStampMilliseconds), C2445NativeMeasurementType.ColdStartTime, null);
    }

    public void trackColdLaunchCancelled(String cause) {
        long stopTimeMs = System.currentTimeMillis();
        Strap strap = Strap.make().mo11639kv("cause", cause);
        this.performanceLogger.markCancelled(COLD_START, strap, Long.valueOf(stopTimeMs), C2445NativeMeasurementType.ColdStartTime, null);
        this.performanceLogger.markCancelled(COLD_START_FIRST_LOADED_ACTIVITY, strap, Long.valueOf(stopTimeMs), C2445NativeMeasurementType.ColdStartTime, null);
        this.hasLoggedFirstActivityLoaded = true;
        this.performanceLogger.markCancelled(COLD_START_FIRST_ACTIVITY_SHOWN, strap, Long.valueOf(stopTimeMs), C2445NativeMeasurementType.ColdStartTime, null);
        this.hasLoggedFirstActivityShown = true;
    }

    public void trackFirstActivityLoaded(String activityName, long stopTimeMs) {
        if (!this.hasLoggedFirstActivityLoaded) {
            this.performanceLogger.markCompleted(COLD_START_FIRST_LOADED_ACTIVITY, Strap.make().mo11639kv("activity", activityName), Long.valueOf(stopTimeMs), C2445NativeMeasurementType.ColdStartTime, null);
            this.hasLoggedFirstActivityLoaded = true;
        }
    }

    public void trackFirstActivityShown(String activityName, long stopTimeMs) {
        if (!this.hasLoggedFirstActivityShown) {
            this.performanceLogger.markCompleted(COLD_START_FIRST_ACTIVITY_SHOWN, Strap.make().mo11639kv("activity", activityName).mo11640kv(KEY_PRELOAD_JODA_TIME, DeferredAppInitExecutor.SHOULD_PRELOAD_JODA).mo11640kv(KEY_PROLOAD_SHARED_PREFERENCES, DeferredAppInitExecutor.SHOULD_PRELOAD_SHARED_PREFERENCES).mo11640kv(KEY_PRELOAD_APP_BOY, DeferredAppInitExecutor.SHOULD_PRELOAD_APP_BOY), Long.valueOf(stopTimeMs), C2445NativeMeasurementType.ColdStartTime, null);
            this.hasLoggedFirstActivityShown = true;
        }
    }
}
