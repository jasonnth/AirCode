package com.airbnb.android.core.instant_promo;

import android.text.TextUtils;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.instant_promo.models.InstantPromotion;
import com.airbnb.android.utils.Strap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class InstantPromotionAnalytics extends BaseAnalytics {
    private static final String ACTION_CLOSE_CLICK = "close_click";
    private static final String ACTION_CTA_CLICK = "cta_click";
    private static final String ACTION_IMPRESSION = "impression";
    private static final String ACTION_PROMO_CAPPED = "promo_capped";
    private static final String ACTION_UNKNOWN_TEMPLATE = "unknown_template_type";
    private static final String CAPPING_DISMISS = "dismiss";
    private static final String CAPPING_EVENT_NAME = "instant_promo_capping";
    private static final String CAPPING_IMPRESSION = "impression";
    private static final String CAPPING_PRIMARY_ACTION = "primary_action";
    private static final String DATADOG_CLOSE_CLICK = "instant_promo_close_click";
    private static final String DATADOG_CTA_CLICK = "instant_promo_cta_click";
    private static final String DATADOG_RENDER_FAILURE = "instant_promo_render_failure";
    private static final String EVENT_NAME = "instant_promotion";
    private static final String PROMOTION_ID = "promo_id";
    private static final String USER_ID = "user_id";

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionKey {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CappingAction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DatadogKey {
    }

    public static void trackPromoImpression(InstantPromotion promotion, long userId) {
        trackPromoEvent("impression", promotion, null, true);
        trackCappingEvent(promotion, userId, "impression");
    }

    public static void trackClickCTA(InstantPromotion promotion, long userId) {
        trackPromoEvent(ACTION_CTA_CLICK, promotion, DATADOG_CTA_CLICK, true);
        trackCappingEvent(promotion, userId, CAPPING_PRIMARY_ACTION);
    }

    public static void trackDismiss(InstantPromotion promotion, long userId) {
        trackPromoEvent(ACTION_CLOSE_CLICK, promotion, DATADOG_CLOSE_CLICK, true);
        trackCappingEvent(promotion, userId, "dismiss");
    }

    public static void trackUnknownTemplateType(InstantPromotion promotion) {
        trackPromoEvent(ACTION_UNKNOWN_TEMPLATE, promotion, DATADOG_RENDER_FAILURE, false);
    }

    private static void trackPromoEvent(String action, InstantPromotion promotion, String datadogKey, boolean isSuccess) {
        Strap strap = Strap.make().mo11639kv("action", action).mo11640kv("is_success", isSuccess);
        if (promotion != null) {
            strap.mo11639kv("surface", promotion.getSurface().key).mo11638kv(PROMOTION_ID, promotion.getId()).mo11639kv("template", promotion.getTemplate().key).mo11639kv("promo_name", promotion.getName());
        }
        if (!TextUtils.isEmpty(datadogKey)) {
            strap.mo11639kv("datadog_key", datadogKey);
            if (promotion != null) {
                strap.mo11639kv("datadog_tags", String.format("platform:android,surface:%s,template:%s", new Object[]{promotion.getSurface().key, promotion.getTemplate().key}));
            }
        }
        AirbnbEventLogger.track(EVENT_NAME, strap);
    }

    private static void trackCappingEvent(InstantPromotion promotion, long userId, String cappingType) {
        if (promotion.isCappingEnabled()) {
            AirbnbEventLogger.trackImmediately(CAPPING_EVENT_NAME, Strap.make().mo11638kv(PROMOTION_ID, promotion.getId()).mo11638kv("user_id", userId).mo11639kv("capping_type", cappingType));
        }
    }
}
