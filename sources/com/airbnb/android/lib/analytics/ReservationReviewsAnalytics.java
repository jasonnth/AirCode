package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.utils.Strap;

public class ReservationReviewsAnalytics extends BaseAnalytics {
    private static final String CURRENT_PAGE = "reservation_reviews";

    public static void trackLoadMoreReviews(ReviewsMode mode, long revieweeId) {
        Strap e;
        Strap e2 = Strap.make().mo11639kv("page", CURRENT_PAGE).mo11637kv("review_mode", mode.ordinal()).mo11639kv(BaseAnalytics.OPERATION, "scroll");
        if (mode == ReviewsMode.MODE_LISTING) {
            e = e2.mo11638kv("listing_id", revieweeId);
        } else {
            e = e2.mo11638kv("user_id", revieweeId);
        }
        AirbnbEventLogger.track("RequestMoreReviews", e);
    }

    public static void trackToggleReservationReviewsTranslate(boolean showTranslatedReview) {
        AirbnbEventLogger.track("TranslationRequestEvent", Strap.make().mo11639kv("page", CURRENT_PAGE).mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, showTranslatedReview ? "see_original_language" : "translate"));
    }
}
