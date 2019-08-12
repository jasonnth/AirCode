package com.airbnb.android.explore.controllers;

import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;

public class ExplorePerformanceAnalytics {
    private static final String LOAD_TIME_HOMES_P4 = "homes_p4_tti";
    private static final String LOAD_TIME_P4 = "p4_tti";
    private static final String LOAD_TIME_SINGLE_TAB = "explore_tti_single_tab";
    private static final String LOAD_TIME_SINGLE_TAB_PREFIX = "explore_tti_";
    private static final String LOAD_TIME_TABS = "explore_tti_all_tabs";
    private boolean hasLoggedP4LoadTime;
    private boolean hasLoggedSingleTabLoadTime;
    private boolean hasLoggedTabsLoadTime;
    private final PerformanceLogger performanceLogger;

    public ExplorePerformanceAnalytics(PerformanceLogger performanceLogger2) {
        this.performanceLogger = performanceLogger2;
    }

    public void trackTabsLoadStart() {
        this.hasLoggedTabsLoadTime = false;
        this.performanceLogger.markStart(LOAD_TIME_TABS);
    }

    public void trackTabsLoadSuccess(boolean cached, String searchId) {
        trackTabsLoadEnd(cached, searchId, true);
    }

    public void trackTabsLoadFailed() {
        trackTabsLoadEnd(false, null, false);
    }

    private void trackTabsLoadEnd(boolean cached, String searchId, boolean success) {
        if (!this.hasLoggedTabsLoadTime) {
            this.hasLoggedTabsLoadTime = true;
            if (!success) {
                this.performanceLogger.remove(LOAD_TIME_TABS);
            } else {
                this.performanceLogger.markCompleted(LOAD_TIME_TABS, Strap.make().mo11640kv("is_cached", cached).mo11639kv(PlacesIntents.INTENT_EXTRA_SEARCH_ID, searchId).mo11640kv("success", success), C2445NativeMeasurementType.TTI, P3Arguments.FROM_EXPLORE);
            }
        }
    }

    public void trackSingleTabLoadStart(String tabId) {
        this.hasLoggedSingleTabLoadTime = false;
        this.performanceLogger.markStart(LOAD_TIME_SINGLE_TAB);
        this.performanceLogger.markStart(LOAD_TIME_SINGLE_TAB_PREFIX + tabId);
    }

    public void trackSingleTabLoadFailed(String tabId) {
        trackSingleTabLoadEnd(false, tabId, null, false);
    }

    public void trackSingleTabLoadSuccess(boolean cached, String tabId, String searchId) {
        trackSingleTabLoadEnd(cached, tabId, searchId, true);
    }

    private void trackSingleTabLoadEnd(boolean cached, String tabId, String searchId, boolean success) {
        if (!this.hasLoggedSingleTabLoadTime) {
            this.hasLoggedSingleTabLoadTime = true;
            if (!success) {
                this.performanceLogger.remove(LOAD_TIME_SINGLE_TAB);
                return;
            }
            Strap strap = Strap.make().mo11640kv("is_cached", cached).mo11639kv("tab_id", tabId).mo11639kv(PlacesIntents.INTENT_EXTRA_SEARCH_ID, searchId).mo11640kv("success", success);
            this.performanceLogger.markCompleted(LOAD_TIME_SINGLE_TAB, strap, C2445NativeMeasurementType.TTI, P3Arguments.FROM_EXPLORE);
            this.performanceLogger.markCompleted(LOAD_TIME_SINGLE_TAB_PREFIX + tabId, strap, C2445NativeMeasurementType.TTI, P3Arguments.FROM_EXPLORE);
        }
    }

    public void trackP4LoadStart(long startTime, boolean isP4Refresh) {
        this.hasLoggedP4LoadTime = false;
        this.performanceLogger.markStart(isP4Refresh ? LOAD_TIME_HOMES_P4 : LOAD_TIME_P4, null, Long.valueOf(startTime));
    }

    public void trackP4LoadEnd(Strap data, boolean isP4Refresh) {
        if (!this.hasLoggedP4LoadTime) {
            this.performanceLogger.markCompleted(isP4Refresh ? LOAD_TIME_HOMES_P4 : LOAD_TIME_P4, data, C2445NativeMeasurementType.TTI, "p4");
            this.hasLoggedP4LoadTime = true;
        }
    }

    public void removeP4LoadTracker() {
        this.performanceLogger.remove(LOAD_TIME_HOMES_P4);
    }
}
