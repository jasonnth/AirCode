package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;

public class CannedMessageAnalytics {
    private static final String EVENT_NAME = "canned_messages";

    private static Strap makeParams(String page, String action, String role) {
        return Strap.make().mo11639kv("event_page", page).mo11639kv("action", action).mo11639kv("role", role);
    }

    public static void trackCannedMessages(String page, String action, String role) {
        AirbnbEventLogger.track(EVENT_NAME, makeParams(page, action, role));
    }

    public static void trackCannedMessages(String page, String action, String role, String message) {
        AirbnbEventLogger.track(EVENT_NAME, makeParams(page, action, role).mo11639kv("message", message));
    }

    public static void trackRemoteCannedMessages(String page, String action, String role) {
        AirbnbEventLogger.track(EVENT_NAME, makeParams(page, action, role).mo11639kv("datadog_key", "host_engagement.template_message." + action));
    }

    public static void trackRemoteCannedMessages(String page, String action, String role, String message) {
        AirbnbEventLogger.track(EVENT_NAME, makeParams(page, action, role).mo11639kv("message", message).mo11639kv("datadog_key", "host_engagement.template_message." + action));
    }
}
