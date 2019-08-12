package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.Review.RatingType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ReferralsIntents {
    public static final String ARG_ENTRY_POINT = "tracking_source";
    public static final String ENTRY_POINT_AIRNAV = "airnav";
    public static final String ENTRY_POINT_DEEP_LINK = "deep_link";
    public static final String ENTRY_POINT_POST_BOOKING = "post_booking";
    public static final String ENTRY_POINT_POST_CONTACT = "post_contact";
    public static final String ENTRY_POINT_POST_P3 = "post_p3_share";
    public static final String ENTRY_POINT_POST_REVIEW = "post_review";
    public static final String ENTRY_POINT_REFERRAL_CODE = "apply_referral_code";
    public static final String ENTRY_POINT_WEB_INTENT = "web_intent";
    private static final int MIN_REFERRAL_POST_REVIEW_OVERRAL_RATINGS = 4;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EntryPoint {
    }

    private ReferralsIntents() {
    }

    public static void startIfNeededFromPostReview(Context context, int reviewRating) {
        if (reviewRating >= 4) {
            context.startActivity(newIntent(context, ENTRY_POINT_POST_REVIEW));
        }
    }

    public static void startIfNeededFromPostReview(Context context, Review review) {
        if (review.isRecommended().booleanValue() && review.getRatingValue(RatingType.Overall).intValue() >= 4) {
            context.startActivity(newIntent(context, ENTRY_POINT_POST_REVIEW));
        }
    }

    public static Intent newIntent(Context context, String entryPoint) {
        return new Intent(context, Activities.referrals()).putExtra(ARG_ENTRY_POINT, entryPoint);
    }

    public static Intent forDeepLink(Context context) {
        return newIntent(context, "deep_link");
    }
}
