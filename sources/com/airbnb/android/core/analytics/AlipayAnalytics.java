package com.airbnb.android.core.analytics;

import android.net.Uri;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.utils.Strap;

public final class AlipayAnalytics extends BaseAnalytics {
    private static final String EVENT_NAME = "alipay_payment_flow";
    private long reservationId;

    public void init(long reservationId2) {
        this.reservationId = reservationId2;
    }

    public void trackBeginFlow() {
        track("begin_flow", "impression");
    }

    public void trackCancelPressed() {
        track("cancel_button", "click");
    }

    public void trackPaymentSuccess() {
        track("payment_succeeded", "success");
    }

    public void trackPaymentFailed() {
        track("payment_failed", BaseAnalytics.FAILURE);
    }

    public void trackPaymentCancelled() {
        track("payment_cancelled", "success");
    }

    public void trackError(String errorMessage) {
        track("error", BaseAnalytics.FAILURE, Strap.make().mo11639kv("error_message", errorMessage));
    }

    public void trackWebPageLoadStart(String url) {
        Strap webPageParam = getWebPageParams(url);
        if (!webPageParam.isEmpty()) {
            track("webpage_loaded", "impression", webPageParam);
        }
    }

    public void trackWebPageLoadFinish(String url) {
        Strap webPageParam = getWebPageParams(url);
        if (!webPageParam.isEmpty()) {
            track("webpage_loaded", "success", webPageParam);
        }
    }

    private static Strap getWebPageParams(String url) {
        Uri uri = Uri.parse(url);
        if (uri.getHost() == null) {
            return Strap.make();
        }
        return Strap.make().mo11639kv("path", uri.getPath()).mo11639kv(TripRole.ROLE_KEY_HOST, uri.getHost());
    }

    private void track(String target, String operation) {
        track(target, operation, null);
    }

    private void track(String target, String operation, Strap additionalParams) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.TARGET, target).mo11639kv(BaseAnalytics.OPERATION, operation).mo11638kv("reservation_id", this.reservationId).mix(additionalParams));
    }
}
