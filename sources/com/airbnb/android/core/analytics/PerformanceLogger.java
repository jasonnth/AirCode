package com.airbnb.android.core.analytics;

import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.utils.ConcurrentUtil;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.airbnb.jitney.event.logging.NativeModeType.p160v1.C2446NativeModeType;
import com.airbnb.jitney.event.logging.Performance.p200v2.NativeMeasurementEvent.Builder;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PerformanceLogger {
    private static final String STATUS_CANCELLED = "cancelled";
    private static final String STATUS_COMPLETED = "completed";
    private static final String TAG = PerformanceLogger.class.getSimpleName();
    private static final long WARN_THRESHOLD = 15000;
    private final LoggingContextFactory loggingContextFactory;
    private final SharedPrefsHelper sharedPrefsHelper;
    private final Map<String, LoggerEventEntry> trackedEvents = new ArrayMap();

    private static class LoggerEventEntry {
        private final Set<String> loggedStages = new HashSet();
        /* access modifiers changed from: private */
        public final Map<String, String> properties;
        /* access modifiers changed from: private */
        public final long timeStamp;

        public LoggerEventEntry(long timeStamp2, Map<String, String> properties2) {
            this.properties = properties2;
            this.timeStamp = timeStamp2;
        }

        public boolean containsStage(String stage) {
            return this.loggedStages.contains(stage);
        }

        public void addStage(String stage) {
            this.loggedStages.add(stage);
        }
    }

    public PerformanceLogger(LoggingContextFactory loggingContextFactory2, SharedPrefsHelper sharedPrefsHelper2) {
        this.loggingContextFactory = loggingContextFactory2;
        this.sharedPrefsHelper = sharedPrefsHelper2;
    }

    public void markStart(String event) {
        markStart(event, null, null);
    }

    public void markStart(String event, Map<String, String> properties, Long startTimeStampMillis) {
        if (startTimeStampMillis == null) {
            startTimeStampMillis = Long.valueOf(System.currentTimeMillis());
        }
        if (this.trackedEvents.containsKey(event)) {
            Log.w(TAG, "Trying to log a duplicate start event. A previous start event with the same name never received a corrresponding stop event. Event name: " + event);
        }
        this.trackedEvents.put(event, new LoggerEventEntry(startTimeStampMillis.longValue(), properties));
    }

    public void markCancelled(String event, C2445NativeMeasurementType measurementType, String view) {
        markCancelled(event, null, null, measurementType, view);
    }

    public void markCancelled(String event, Map<String, String> properties, C2445NativeMeasurementType measurementType, String view) {
        markCancelled(event, properties, null, measurementType, view);
    }

    public void markCancelled(String event, Map<String, String> properties, Long stopTimeStampMillis, C2445NativeMeasurementType measurementType, String view) {
        markStop(event, "cancelled", properties, stopTimeStampMillis, measurementType, view);
    }

    public void markCompleted(String event, C2445NativeMeasurementType measurementType, String view) {
        markCompleted(event, null, null, measurementType, view);
    }

    public void markCompleted(String event, Map<String, String> properties, C2445NativeMeasurementType measurementType, String view) {
        markStop(event, "completed", properties, null, measurementType, view);
    }

    public void markCompleted(String event, Map<String, String> properties, Long stopTimeStampMills, C2445NativeMeasurementType measurementType, String view) {
        markStop(event, "completed", properties, stopTimeStampMills, measurementType, view);
    }

    private void markStop(String eventIdentifier, String status, Map<String, String> stopEventExtraProperties, Long stopTimeStampMills, C2445NativeMeasurementType measurementType, String view) {
        if (stopTimeStampMills == null) {
            stopTimeStampMills = Long.valueOf(System.currentTimeMillis());
        }
        LoggerEventEntry startEvent = (LoggerEventEntry) this.trackedEvents.remove(eventIdentifier);
        if (startEvent == null) {
            Log.w(TAG, "Trying to log a stop eventIdentifier with no corresponsing start eventIdentifier. Event name: " + eventIdentifier);
            return;
        }
        long eventDuration = stopTimeStampMills.longValue() - startEvent.timeStamp;
        String str = eventIdentifier;
        logPerformance(str, Long.valueOf(eventDuration), status, Strap.make().mix(startEvent.properties).mix(stopEventExtraProperties), measurementType, view);
    }

    public void logTimelineStage(String eventIdentifier, String stageIdentifier, Map<String, String> extraProperties, String view) {
        long stopTimeStampMills = System.currentTimeMillis();
        LoggerEventEntry startEvent = (LoggerEventEntry) this.trackedEvents.get(eventIdentifier);
        if (startEvent == null) {
            Log.w(TAG, "Trying to log a stop eventIdentifier with no corresponsing start eventIdentifier. Event name: " + eventIdentifier);
        } else if (!startEvent.containsStage(stageIdentifier)) {
            startEvent.addStage(stageIdentifier);
            long eventDuration = stopTimeStampMills - startEvent.timeStamp;
            logPerformance(eventIdentifier + "_" + stageIdentifier, Long.valueOf(eventDuration), "completed", Strap.make().mix(startEvent.properties).mix(extraProperties), C2445NativeMeasurementType.TTI, view);
        }
    }

    private void logPerformance(String eventIdentifier, Long eventDuration, String status, Strap properties, C2445NativeMeasurementType measurementType, String view) {
        ConcurrentUtil.deferParallel(PerformanceLogger$$Lambda$1.lambdaFactory$(this, eventIdentifier, eventDuration, status, properties, measurementType, view));
    }

    /* access modifiers changed from: private */
    public void logPerformanceImpl(String eventIdentifier, Long eventDuration, String status, Strap properties, C2445NativeMeasurementType measurementType, String view) {
        Strap properties2;
        if (eventDuration.longValue() < 0) {
            Log.w(TAG, "Trying to log an eventIdentifier with a negative duration. Event name: " + eventIdentifier + ", duration: " + eventDuration);
        } else if (measurementType == null) {
            Log.w(TAG, "Trying to log an eventIdentifier without specifying a measurement type. Event name: " + eventIdentifier);
        } else {
            if (properties == null) {
                properties2 = Strap.make();
            } else {
                properties2 = MiscUtils.sanitize(properties);
            }
            logMeasurementEventJitney(eventIdentifier, eventDuration.longValue(), properties2, measurementType, view);
            logPerformanceEvent(properties2.mo11639kv(BaseAnalytics.OPERATION, eventIdentifier).mo11638kv("value", eventDuration.longValue()).mo11639kv("status", status));
            if (eventDuration.longValue() > WARN_THRESHOLD && isCompleted(status) && shouldWarn(eventIdentifier)) {
                BugsnagWrapper.notify(new Throwable("[Perf] " + eventIdentifier + " takes > 15s to load, actual duration (in ms) = " + eventDuration));
            }
        }
    }

    public void remove(String eventIdentifier) {
        this.trackedEvents.remove(eventIdentifier);
    }

    private boolean shouldWarn(String event) {
        return AppLaunchAnalytics.COLD_START.equalsIgnoreCase(event) || !BuildHelper.isReleaseBuild();
    }

    private void logPerformanceEvent(Strap properties) {
        AirbnbEventLogger.track("perf_logger", properties);
    }

    private void logMeasurementEventJitney(String eventIdentifier, long duration, Strap props, C2445NativeMeasurementType measurementType, String view) {
        C2445NativeMeasurementType nativeMeasurementType;
        Context newInstance = this.loggingContextFactory.newInstance();
        if (measurementType == null) {
            nativeMeasurementType = C2445NativeMeasurementType.TTI;
        } else {
            nativeMeasurementType = measurementType;
        }
        JitneyPublisher.publish(new Builder(newInstance, nativeMeasurementType, eventIdentifier, Long.valueOf(duration), this.sharedPrefsHelper.isGuestMode() ? C2446NativeModeType.Guest : C2446NativeModeType.Host).view(view).information(MiscUtils.sanitize(props)));
    }

    private boolean isCompleted(String status) {
        return "completed".equals(status);
    }
}
