package com.airbnb.android.core.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;

public class FiveAxiomAnalytics extends BaseAnalytics {
    private static final String BUTTON_NAME = "btn_name";
    private static final String COMPLETE = "complete";
    private static final String EVENT_NAME = "five_axiom_china";
    private static final String FLOW_CANCELLED = "flow_cancelled";
    private static final String FLOW_FINISHED = "flow_finished";
    private static final String FLOW_START = "flow_start";
    private static final String LAUNCH = "launch";
    private static final String STEP_CANCELLED = "step_cancelled";
    private static final String STEP_FINISHED = "step_finished";
    private static final String STEP_NAME = "step_name";
    private static final String STEP_START = "step_start";

    public static void trackLaunch() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, LAUNCH));
    }

    public static void trackComplete() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, COMPLETE));
    }

    public static void trackFlowStart() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, FLOW_START));
    }

    public static void trackFlowFinished() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, FLOW_FINISHED));
    }

    public static void trackFlowCancelled() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, FLOW_CANCELLED));
    }

    public static void trackStepStart(String step) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, STEP_START).mo11639kv(STEP_NAME, step));
    }

    public static void trackStepCancelled(String step) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, STEP_CANCELLED).mo11639kv(STEP_NAME, step));
    }

    public static void trackStepFinished(String step) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, STEP_FINISHED).mo11639kv(STEP_NAME, step));
    }

    public static void trackClick(String btnName) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BUTTON_NAME, btnName));
    }

    public static void trackSuccess(String eventName) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, eventName).mo11639kv(BaseAnalytics.OPERATION, "success"));
    }

    public static void trackFailure(String eventName) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, eventName).mo11639kv(BaseAnalytics.OPERATION, BaseAnalytics.FAILURE));
    }
}
