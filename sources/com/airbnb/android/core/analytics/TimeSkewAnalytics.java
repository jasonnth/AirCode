package com.airbnb.android.core.analytics;

import android.content.SharedPreferences;
import android.os.SystemClock;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.services.NetworkTimeProvider;
import com.airbnb.android.utils.ConcurrentUtil;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class TimeSkewAnalytics {
    private static final String EXECUTOR_TAG = "time_skew_analytics_executor";
    private static final String NETWORK_TIME = "mobile_time_skew_diagnostics_network_time";
    public static final String PREFS_CORRECTED_TIME_NTP_OFFSET = "ntp_offset_in_milli_sec";
    public static final String PREFS_CORRECTED_TIME_SYSTEM_TIME = "system_time_in_milli_sec";
    public static final String PREFS_CORRECTED_TIME_UP_TIME = "up_time_in_milli_sec";
    private static final String TIME_RESOURCES = "mobile_time_skew_diagnostics_time_resources";
    private static final long TIME_SKEW_TOLERANCE = 60000;
    private final AirbnbPreferences airbnbPreferences;
    private final NetworkTimeProvider networkTimeProvider;
    private Executor requestExecutor;

    public TimeSkewAnalytics(AirbnbPreferences airbnbPreferences2, NetworkTimeProvider networkTimeProvider2) {
        this.airbnbPreferences = airbnbPreferences2;
        this.networkTimeProvider = networkTimeProvider2;
    }

    private void clearTimeMeasurementCacheIfNeeded() {
        SharedPreferences sharedPreferences = this.airbnbPreferences.getSharedPreferences();
        String cachedSystemTime = sharedPreferences.getString(PREFS_CORRECTED_TIME_SYSTEM_TIME, null);
        String cachedUpTime = sharedPreferences.getString(PREFS_CORRECTED_TIME_UP_TIME, null);
        if (cachedSystemTime != null && cachedUpTime != null) {
            if (Math.abs((System.currentTimeMillis() - Long.parseLong(cachedSystemTime)) - (SystemClock.elapsedRealtime() - Long.parseLong(cachedUpTime))) > 60000) {
                sharedPreferences.edit().remove(PREFS_CORRECTED_TIME_NTP_OFFSET).apply();
            }
        }
    }

    public void requestNetworkTime() {
        clearTimeMeasurementCacheIfNeeded();
        if (this.requestExecutor == null) {
            this.requestExecutor = Executors.newSingleThreadExecutor();
            ConcurrentUtil.deferOnExecutor(TimeSkewAnalytics$$Lambda$1.lambdaFactory$(this), this.requestExecutor);
        }
        ConcurrentUtil.deferOnExecutor(TimeSkewAnalytics$$Lambda$2.lambdaFactory$(this), this.requestExecutor);
    }

    /* access modifiers changed from: private */
    public void doRequest() {
        try {
            updateTimeMeasurement(System.currentTimeMillis() - this.networkTimeProvider.getNetworkTime());
        } catch (IOException e) {
        }
    }

    private void updateTimeMeasurement(long systemTimeAhead) {
        this.airbnbPreferences.getSharedPreferences().edit().putString(PREFS_CORRECTED_TIME_SYSTEM_TIME, Long.toString(System.currentTimeMillis())).putString(PREFS_CORRECTED_TIME_UP_TIME, Long.toString(SystemClock.elapsedRealtime())).putString(PREFS_CORRECTED_TIME_NTP_OFFSET, Long.toString(systemTimeAhead)).apply();
    }

    public long getCorrectedTime() {
        String systemTimeAhead = this.airbnbPreferences.getSharedPreferences().getString(PREFS_CORRECTED_TIME_NTP_OFFSET, null);
        if (systemTimeAhead != null) {
            return System.currentTimeMillis() - Long.parseLong(systemTimeAhead);
        }
        return 0;
    }

    public Map<String, String> getCorrectedTimeForJitney() {
        return Collections.singletonMap("corrected_time", Long.toString(getCorrectedTime()));
    }
}
