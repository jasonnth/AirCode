package com.jumio.analytics;

import android.graphics.PointF;
import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.common.util.UriUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;

public class MobileEvents {
    public static final int EVENTTYPE_ANDROID_LIFECYCLE = 303;
    public static final int EVENTTYPE_EPASSPORT = 308;
    public static final int EVENTTYPE_EXCEPTION = 305;
    public static final int EVENTTYPE_GENERIC = 0;
    public static final int EVENTTYPE_MOBILE_DEVICE_INFO = 307;
    public static final int EVENTTYPE_NETWORKCALL = 309;
    public static final int EVENTTYPE_PAGEVIEW = 300;
    public static final int EVENTTYPE_RAW_TOUCH = 304;
    public static final int EVENTTYPE_SDKLIFECYCLE = 302;
    public static final int EVENTTYPE_SDKPARAMETERS = 306;
    public static final int EVENTTYPE_USERACTION = 301;
    private static Screen currentScreen;

    public static AnalyticsEvent pageView(UUID sessionId, Screen screen, MetaInfo metaInfo) {
        currentScreen = screen;
        return new AnalyticsEvent(300, sessionId, screen.toString(), metaInfo);
    }

    public static AnalyticsEvent sdkLifecycle(UUID sessionId, DismissType dt) {
        return new AnalyticsEvent(EVENTTYPE_SDKLIFECYCLE, sessionId, dt.toString(), null);
    }

    public static AnalyticsEvent userAction(UUID sessionId, Screen screen, UserAction action) {
        MetaInfo meta = new MetaInfo();
        addViewNameToMetaIfAvailable(meta, screen);
        return new AnalyticsEvent(EVENTTYPE_USERACTION, sessionId, action.toString(), meta);
    }

    public static AnalyticsEvent userAction(UUID sessionId, Screen screen, UserAction action, String additionalData) {
        MetaInfo meta = new MetaInfo();
        addViewNameToMetaIfAvailable(meta, screen);
        if (additionalData != null) {
            meta.put("additionalData", additionalData);
        }
        return new AnalyticsEvent(EVENTTYPE_USERACTION, sessionId, action.toString(), meta);
    }

    private static void addViewNameToMetaIfAvailable(MetaInfo metaInfo, Screen screen) {
        if (screen == null) {
            screen = currentScreen;
        }
        if (screen != null) {
            metaInfo.put("view", screen.toString());
        }
    }

    public static AnalyticsEvent userAction(UUID sessionId, Screen viewName, UserAction action, MetaInfo meta) {
        if (viewName == null) {
            viewName = currentScreen;
        }
        if (viewName != null) {
            meta.put("view", viewName.toString());
        }
        return new AnalyticsEvent(EVENTTYPE_USERACTION, sessionId, action.toString(), meta);
    }

    public static AnalyticsEvent androidLifecycle(UUID sessionId, String viewName, String lifecycleMethod) {
        return new AnalyticsEvent(303, sessionId, viewName + "." + lifecycleMethod, null);
    }

    public static AnalyticsEvent sdkParameters(UUID sessionId, MetaInfo sdkParameterMap) {
        new JSONObject(sdkParameterMap);
        return new AnalyticsEvent(EVENTTYPE_SDKPARAMETERS, sessionId, "noValue", sdkParameterMap);
    }

    public static AnalyticsEvent mobileDeviceInformation(UUID sessionId, String sdkVersionString, boolean hasDeviceNfc, boolean wasNfcEnabled) {
        String os = InternalLogger.EVENT_PARAM_SDK_ANDROID;
        String osVersion = String.valueOf(VERSION.SDK_INT);
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        MetaInfo meta = new MetaInfo();
        meta.put("sdk-version", sdkVersionString);
        meta.put("os", os);
        meta.put("os-version", osVersion);
        meta.put("manufacturer", manufacturer);
        meta.put("model", model);
        meta.put("hasDeviceNFC", Boolean.valueOf(hasDeviceNfc));
        meta.put("wasNFCenabled", Boolean.valueOf(wasNfcEnabled));
        return new AnalyticsEvent(307, sessionId, "noValue", meta);
    }

    public static AnalyticsEvent epassport(UUID sessionId, int numCsca, boolean hasUsedEpassport, String scanref, String errorString) {
        MetaInfo meta = new MetaInfo();
        meta.put("numCscaFound", String.valueOf(numCsca));
        meta.put("hasUsedEpassport", String.valueOf(hasUsedEpassport));
        meta.put("scanreference", scanref);
        if (errorString != null && errorString.length() > 0) {
            meta.put("error", errorString);
        }
        return new AnalyticsEvent(308, sessionId, "epassport", meta);
    }

    public static AnalyticsEvent exception(UUID sessionId, Exception ex) {
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        return new AnalyticsEvent(EVENTTYPE_EXCEPTION, sessionId, writer.toString(), null);
    }

    public static AnalyticsEvent rawTouch(UUID sessionId, List<PointF> touchCoords) {
        StringBuilder bldr = new StringBuilder();
        for (PointF p : touchCoords) {
            bldr.append(String.format("%.2f:%.2f", new Object[]{Float.valueOf(p.x), Float.valueOf(p.y)})).append(",");
        }
        return new AnalyticsEvent(EVENTTYPE_RAW_TOUCH, sessionId, bldr.toString(), null);
    }

    public static AnalyticsEvent networkRequest(UUID sessionId, String requestName, int httpCode, String message, long roundtripTime) {
        MetaInfo meta = new MetaInfo();
        meta.put(UriUtil.HTTP_SCHEME, String.valueOf(httpCode));
        meta.put("message", message);
        meta.put("roundtrip", String.valueOf(roundtripTime));
        return new AnalyticsEvent(EVENTTYPE_NETWORKCALL, sessionId, requestName, meta);
    }
}
