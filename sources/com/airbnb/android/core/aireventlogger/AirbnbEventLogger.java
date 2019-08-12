package com.airbnb.android.core.aireventlogger;

import android.content.Context;
import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.util.SimpleArrayMap;
import com.airbnb.android.aireventlogger.AirEvent;
import com.airbnb.android.aireventlogger.LogAir;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.utils.Strap;
import com.microsoft.thrifty.Struct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AirbnbEventLogger {
    public static final String AIREVENTS_TARGET = "airevents";
    public static final String EVENT_ENGINEERING_LOG = "android_eng";
    public static final String EVENT_ENGINEERING_LOG_2 = "android_eng2";
    public static final String JITNEY_JSON_TARGET = "jitney_json";
    public static final String JITNEY_THRIFT_TARGET = "jitney_thrift";
    private static final int ON_RESUME_QUEUE_SIZE = 4;
    private static final String TAG = AirbnbEventLogger.class.getSimpleName();
    private final AirbnbAccountManager accountManager;
    private final AffiliateInfo affiliateInfo;
    private final LoggingContextFactory loggingContextFactory;
    private final String[] onResumeHistory = new String[4];
    private final TimeSkewAnalytics timeSkewAnalytics;

    public static class Builder {
        private final Map<String, Object> data = new ArrayMap();
        private boolean logToDatadog = false;
        private String name;

        public Builder name(String name2) {
            this.name = name2;
            return this;
        }

        public Builder mix(ParcelStrap strap) {
            if (strap != null) {
                for (Entry<String, String> entry : strap.internal().entrySet()) {
                    if (!this.data.containsKey(entry.getKey())) {
                        this.data.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            return this;
        }

        /* renamed from: kv */
        public Builder mo8238kv(String k, String v) {
            this.data.put(k, v);
            return this;
        }

        /* renamed from: kv */
        public Builder mo8237kv(String k, long v) {
            mo8238kv(k, Long.toString(v));
            return this;
        }

        /* renamed from: kv */
        public Builder mo8236kv(String k, int v) {
            mo8238kv(k, Integer.toString(v));
            return this;
        }

        /* renamed from: kv */
        public Builder mo8239kv(String k, boolean v) {
            mo8238kv(k, Boolean.toString(v));
            return this;
        }

        /* renamed from: kv */
        public Builder mo8235kv(String k, float v) {
            mo8238kv(k, Float.toString(v));
            return this;
        }

        /* renamed from: kv */
        public Builder mo8234kv(String k, double v) {
            mo8238kv(k, Double.toString(v));
            return this;
        }

        public Builder logToDatadog() {
            this.logToDatadog = true;
            return this;
        }

        public void track() {
            AirbnbEventLogger.getInstance().doTrack((String) Check.notNull(this.name, "name == null"), this.data, this.logToDatadog);
        }
    }

    public AirbnbEventLogger(AirbnbAccountManager accountManager2, AffiliateInfo affiliateInfo2, TimeSkewAnalytics timeSkewAnalytics2, LoggingContextFactory loggingContextFactory2) {
        this.accountManager = accountManager2;
        this.affiliateInfo = affiliateInfo2;
        this.timeSkewAnalytics = timeSkewAnalytics2;
        this.loggingContextFactory = loggingContextFactory2;
        Arrays.fill(this.onResumeHistory, "");
    }

    /* access modifiers changed from: private */
    public static AirbnbEventLogger getInstance() {
        return CoreApplication.instance().component().airbnbEventLogger();
    }

    private AirbnbEvent createEventFromData(Context context, String event, Map<String, Object> eventData, String[] onResumeHistory2, boolean logToDatadog) {
        if (logToDatadog) {
            eventData.put("forward_to_datadog", Boolean.valueOf(true));
        }
        eventData.put("corrected_time", Long.valueOf(this.timeSkewAnalytics.getCorrectedTime()));
        return new AirbnbEvent(context, event, eventData, this.accountManager.getCurrentUser(), this.affiliateInfo.getAffiliateCampaignData(), onResumeHistory2);
    }

    public static Builder event() {
        return new Builder();
    }

    public static void track(String eventName, Map<String, Object> eventData) {
        getInstance().doTrack(eventName, eventData, false);
    }

    public static void trackJitneyJSON(Map<String, Object> eventData) {
        getInstance().doTrackJitneyJSON(eventData);
    }

    public static void track(Struct eventStruct) {
        getInstance().doTrack(eventStruct);
    }

    public static void track(String eventName, Strap eventData) {
        getInstance().doTrack(eventName, new ArrayMap((SimpleArrayMap) eventData), false);
    }

    public static void trackImmediately(Struct eventStruct) {
        getInstance().doTrackImmediately(eventStruct);
    }

    public static void trackImmediately(String eventName, Strap eventData) {
        getInstance().trackImmediately(eventName, new ArrayMap((SimpleArrayMap) eventData), false);
    }

    public static void track(String eventName, Map<String, Object> eventData, boolean logToDatadog) {
        getInstance().doTrack(eventName, eventData, logToDatadog);
    }

    public static void track(String eventName, Strap eventData, boolean logToDatadog) {
        getInstance().doTrack(eventName, new ArrayMap((SimpleArrayMap) eventData), logToDatadog);
    }

    public static void track(String... path) {
        Map<String, Object> strap = new ArrayMap<>();
        for (int i = 0; i < path.length - 1; i++) {
            String currentPath = path[i + 1];
            if (currentPath == null) {
                currentPath = "";
            }
            strap.put("c" + (i + 2), currentPath);
        }
        track(path[0], strap);
    }

    public static void trackOnResume(String className) {
        getInstance().doTrackOnResume(className);
    }

    private AirbnbEvent createAirbnbEvent(String eventName, Map<String, Object> eventData, boolean logToDatadog) {
        if (!shouldTrack()) {
            return null;
        }
        handleDebugFeatures(eventName, eventData);
        return createEventFromData(CoreApplication.appContext(), eventName, eventData != null ? eventData : new HashMap<>(), this.onResumeHistory, logToDatadog);
    }

    /* access modifiers changed from: 0000 */
    public void doTrack(String eventName, Map<String, Object> eventData, boolean logToDatadog) {
        AirbnbEvent event = createAirbnbEvent(eventName, eventData, logToDatadog);
        if (event != null) {
            LogAir.track(new AirEvent(event, AIREVENTS_TARGET));
        }
    }

    /* access modifiers changed from: 0000 */
    public void trackImmediately(String eventName, Map<String, Object> eventData, boolean logToDatadog) {
        AirbnbEvent event = createAirbnbEvent(eventName, eventData, logToDatadog);
        if (event != null) {
            LogAir.trackImmediately(new AirEvent(event, AIREVENTS_TARGET));
        }
    }

    /* access modifiers changed from: 0000 */
    public void doTrack(Struct struct) {
        if (shouldTrack()) {
            handleDebugFeatures(struct.getClass().getSimpleName(), struct);
            LogAir.track(new AirEvent(struct, JITNEY_THRIFT_TARGET));
        }
    }

    private void doTrackImmediately(Struct struct) {
        if (shouldTrack()) {
            handleDebugFeatures(struct.getClass().getSimpleName(), struct);
            LogAir.trackImmediately(new AirEvent(struct, JITNEY_THRIFT_TARGET));
        }
    }

    /* access modifiers changed from: 0000 */
    public void doTrackJitneyJSON(Map<String, Object> eventData) {
        LogAir.track(new AirEvent(new JitneyJSONEvent(eventData, this.loggingContextFactory.newInstance()), JITNEY_JSON_TARGET));
    }

    private void handleDebugFeatures(String eventName, Object eventData) {
        if (BuildHelper.isDebugFeaturesEnabled() || BuildHelper.isBeta()) {
            C0715L.m1189d(TAG, "Logging to air_events with event_name='" + eventName + "' and event_data=" + eventData.toString());
        }
    }

    private boolean shouldTrack() {
        return !MiscUtils.isUserAMonkey();
    }

    private void doTrackOnResume(String className) {
        System.arraycopy(this.onResumeHistory, 0, this.onResumeHistory, 1, 3);
        this.onResumeHistory[0] = className;
    }
}
