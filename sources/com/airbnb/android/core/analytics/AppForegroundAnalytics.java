package com.airbnb.android.core.analytics;

import android.app.Activity;
import android.content.Intent;
import com.airbnb.android.core.AppForegroundDetector.AppForegroundListener;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.requests.PushNotificationConversionRequest;
import com.airbnb.android.core.services.PushIntentServiceConstants;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherConstants;
import com.airbnb.android.utils.Strap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AppForegroundAnalytics extends BaseAnalytics implements AppForegroundListener {
    private static final String DEEPLINK = "deeplink";
    private static final String DEFAULT = "default";
    private static final String HANDLED_PARAM = "handled";
    private static final String OPEN_ORIGIN_PARAM = "open_origin";
    private static final String PUSH = "push";
    private static final String PUSH_ID_PARAM = "push_id";
    private static final String PUSH_TYPE_PARAM = "push_type";
    private static final String URI_PARAM = "uri";
    private static final String WEBLINK = "weblink";
    private final TimeSkewAnalytics timeSkewAnalytics;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OpenOrigin {
    }

    public AppForegroundAnalytics(TimeSkewAnalytics timeSkewAnalytics2) {
        this.timeSkewAnalytics = timeSkewAnalytics2;
    }

    public void onAppForegrounded(Activity entryActivity) {
        Strap metadata = Strap.make();
        Intent intent = entryActivity.getIntent();
        metadata.mo11639kv(BaseAnalytics.OPERATION, "app_foreground");
        if (DeepLinkUtils.isDeepLink(intent)) {
            addOriginMetadata(metadata, DEEPLINK);
            metadata.mo11639kv("uri", intent.getStringExtra("deep_link_uri"));
            metadata.mo11640kv(HANDLED_PARAM, intent.getBooleanExtra("com.airbnb.deeplinkdispatch.EXTRA_SUCCESSFUL", true));
        } else if (intent.getBooleanExtra(WebIntentMatcherConstants.IS_WEB_LINK, false)) {
            addOriginMetadata(metadata, WEBLINK);
            metadata.mo11639kv("uri", intent.getStringExtra("uri"));
        } else if (intent.getBooleanExtra(PushIntentServiceConstants.EXTRA_IS_PUSH, false)) {
            addOriginMetadata(metadata, PUSH);
            metadata.mo11639kv(PUSH_ID_PARAM, intent.getStringExtra(PushNotificationConversionRequest.PUSH_NOTIFICATION_ID_KEY));
            metadata.mo11639kv("push_type", intent.getStringExtra(PushIntentServiceConstants.EXTRA_PUSH_TYPE));
            metadata.mo11640kv(HANDLED_PARAM, intent.getBooleanExtra(PushIntentServiceConstants.EXTRA_HANDLED, false));
        } else {
            addOriginMetadata(metadata, DEFAULT);
        }
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, addDeviceType(entryActivity, metadata));
        this.timeSkewAnalytics.requestNetworkTime();
    }

    public void onAppBackgrounded() {
    }

    private static void addOriginMetadata(Strap strap, String origin) {
        strap.mo11639kv(OPEN_ORIGIN_PARAM, origin);
    }
}
