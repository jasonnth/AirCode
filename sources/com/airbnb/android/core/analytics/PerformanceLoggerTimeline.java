package com.airbnb.android.core.analytics;

import com.airbnb.android.core.CoreApplication;
import java.util.Map;

public class PerformanceLoggerTimeline {
    private static final PerformanceLogger performanceLogger = CoreApplication.instance().component().performanceLogger();

    public enum Event {
        HOST_INBOX("host_inbox"),
        HOST_MANAGE_LISTING_PICKER("host_manage_listing_picker"),
        HOST_LISTING("host_listing"),
        HOST_CALENDAR_AGENDA("host_calendar_agenda"),
        HOST_CALENDAR_SINGLE("host_calendar_single"),
        HOST_STATS_SUMMARY("host_stats_summary"),
        HOST_RESERVATION("host_reservation"),
        HOST_MESSAGE_THREAD("host_message_thread");
        
        public final String eventName;

        private Event(String eventName2) {
            this.eventName = eventName2;
        }
    }

    public enum Stage {
        TIME_TO_INTERACTION("tti");
        
        public final String name;

        private Stage(String name2) {
            this.name = name2;
        }
    }

    public static void start(Event event) {
        start(event, null, null);
    }

    public static void start(Event event, Map<String, String> properties, Long startTimeStampMillis) {
        clearTimelineEvents();
        performanceLogger.markStart(event.eventName, properties, startTimeStampMillis);
    }

    public static void logTimeToInteraction(Event event) {
        logTimeToInteraction(event, null, null);
    }

    public static void logTimeToInteraction(Event event, Map<String, String> extraProperties, String view) {
        performanceLogger.logTimelineStage(event.eventName, Stage.TIME_TO_INTERACTION.name, extraProperties, view);
    }

    private static void clearTimelineEvents() {
        for (Event e : Event.values()) {
            performanceLogger.remove(e.eventName);
        }
    }
}
