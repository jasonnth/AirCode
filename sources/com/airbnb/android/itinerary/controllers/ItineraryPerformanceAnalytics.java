package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;

public class ItineraryPerformanceAnalytics {
    private static final String LOAD_TIME_TIMELINE = "itinerary_tti_timeline";
    private static final String LOAD_TIME_TRIP_EVENT = "itinerary_tti_trip_event";
    private boolean hasLoggedTimelineLoadTime;
    private boolean hasLoggedTripEventLoadTime;
    private final PerformanceLogger performanceLogger;

    public ItineraryPerformanceAnalytics(PerformanceLogger performanceLogger2) {
        this.performanceLogger = performanceLogger2;
    }

    public void trackTimelineLoadStart() {
        this.hasLoggedTimelineLoadTime = false;
        this.performanceLogger.markStart(LOAD_TIME_TIMELINE);
    }

    public void trackTimelineLoadSuccess() {
        trackTimelineLoadEnd(true);
    }

    public void trackTimelineLoadFailed() {
        trackTimelineLoadEnd(false);
    }

    private void trackTimelineLoadEnd(boolean success) {
        if (!this.hasLoggedTimelineLoadTime) {
            this.performanceLogger.markCompleted(LOAD_TIME_TIMELINE, Strap.make().mo11640kv("success", success), C2445NativeMeasurementType.TTI, "itinerary");
            this.hasLoggedTimelineLoadTime = true;
        }
    }

    public void trackTripEventLoadStart() {
        this.hasLoggedTripEventLoadTime = false;
        this.performanceLogger.markStart(LOAD_TIME_TRIP_EVENT);
    }

    public void trackTripEventLoadSuccess() {
        trackTripEventLoadEnd(true);
    }

    public void trackTripEventLoadFailed() {
        trackTripEventLoadEnd(false);
    }

    private void trackTripEventLoadEnd(boolean success) {
        if (!this.hasLoggedTripEventLoadTime) {
            this.performanceLogger.markCompleted(LOAD_TIME_TRIP_EVENT, Strap.make().mo11640kv("success", success), C2445NativeMeasurementType.TTI, "itinerary");
            this.hasLoggedTripEventLoadTime = true;
        }
    }

    public void trackItineraryLoadSuccess(String tag) {
        if (ItineraryNavigationController.FRAGMENT_TIMELINE_TAG.equals(tag)) {
            trackTimelineLoadSuccess();
        } else if (ItineraryNavigationController.FRAGMENT_TRIP_TAG.equals(tag)) {
            trackTripEventLoadSuccess();
        }
    }
}
