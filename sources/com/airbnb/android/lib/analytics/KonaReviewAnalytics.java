package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.utils.Strap;

public class KonaReviewAnalytics extends BaseAnalytics {
    private static final String CONFIRMATION_CODE = "confirmation_code";
    private static final String EDIT_FEEDBACK_BUTTON = "edit_feedback_button";
    private static final String EDIT_REVIEW_BUTTON = "edit_review_button";
    private static final String GET_STARTED_BUTTON = "get_started_button";
    private static final String LEAVE_FEEDBACK_BUTTON = "leave_feedback_button";
    private static final String NEXT_BUTTON = "next_button";
    private static final String NPS = "nps";
    private static final String RATING_BUTTON = "rating_button";
    private static final String REVIEWS = "reviews";
    private static final String REVIEWS_FEEDBACK = "reviews_feedback";
    private static final String REVIEWS_PRIVATE_FEEDBACK = "reviews_private_feedback";
    private static final String REVIEWS_RATING = "reviews_rating";
    private static final String REVIEWS_START = "reviews_start";
    private static final String REVIEWS_SUBMIT = "reviews_submit";
    private static final String REVIEW_CATEGORY = "review_category";
    private static final String REVIEW_ID = "review_id";
    private static final String ROLE = "role";
    private static final String SKIP_BUTTON = "skip_button";
    private static final String SUBMIT_REVIEW_BUTTON = "submit_review_button";

    private static void track(Review review, Strap strap) {
        String confirmationCode;
        if (review.getReservation() == null) {
            confirmationCode = "android_no_conf_code";
        } else {
            confirmationCode = review.getReservation().getConfirmationCode();
        }
        AirbnbEventLogger.track("reviews", strap.mo11638kv(REVIEW_ID, review.getId()).mo11639kv(ROLE, review.getReviewRole().apiString).mo11639kv("confirmation_code", confirmationCode));
    }

    private static Strap makeStrap(String action, String page, String target) {
        return Strap.make().mo11639kv("action", action).mo11639kv("page", page).mo11639kv(BaseAnalytics.TARGET, target);
    }

    public static void trackStartImpression(Review review) {
        track(review, makeStrap("impression", REVIEWS_START, ""));
    }

    public static void trackStartClick(Review review) {
        track(review, makeStrap("click", REVIEWS_START, GET_STARTED_BUTTON));
    }

    public static void trackFeedbackImpression(Review review) {
        track(review, makeStrap("impression", REVIEWS_FEEDBACK, ""));
    }

    public static void trackFeedbackClickLeave(Review review) {
        track(review, makeStrap("click", REVIEWS_FEEDBACK, LEAVE_FEEDBACK_BUTTON));
    }

    public static void trackFeedbackClickEdit(Review review) {
        track(review, makeStrap("click", REVIEWS_FEEDBACK, EDIT_FEEDBACK_BUTTON));
    }

    public static void trackFeedbackSubmit(Review review) {
        track(review, makeStrap(BaseAnalytics.SUBMIT, REVIEWS_FEEDBACK, "next_button"));
    }

    public static void trackPrivateImpression(Review review) {
        track(review, makeStrap("impression", REVIEWS_PRIVATE_FEEDBACK, ""));
    }

    public static void trackPrivateClickLeave(Review review) {
        track(review, makeStrap("click", REVIEWS_PRIVATE_FEEDBACK, LEAVE_FEEDBACK_BUTTON));
    }

    public static void trackPrivateClickEdit(Review review) {
        track(review, makeStrap("click", REVIEWS_PRIVATE_FEEDBACK, EDIT_FEEDBACK_BUTTON));
    }

    public static void trackPrivateClickSkip(Review review) {
        track(review, makeStrap("click", REVIEWS_PRIVATE_FEEDBACK, SKIP_BUTTON));
    }

    public static void trackPrivateSubmit(Review review) {
        track(review, makeStrap(BaseAnalytics.SUBMIT, REVIEWS_PRIVATE_FEEDBACK, "next_button"));
    }

    public static void trackRatingImpression(Review review, String category) {
        track(review, makeStrap("impression", REVIEWS_RATING, "").mo11639kv(REVIEW_CATEGORY, category));
    }

    public static void trackRatingClick(Review review, String category) {
        track(review, makeStrap("click", REVIEWS_RATING, RATING_BUTTON).mo11639kv(REVIEW_CATEGORY, category));
    }

    public static void trackRatingSubmit(Review review, String category) {
        track(review, makeStrap(BaseAnalytics.SUBMIT, REVIEWS_RATING, "next_button").mo11639kv(REVIEW_CATEGORY, category));
    }

    public static void trackSummaryImpression(Review review) {
        track(review, makeStrap("impression", REVIEWS_SUBMIT, ""));
    }

    public static void trackSummaryClick(Review review) {
        track(review, makeStrap("click", REVIEWS_SUBMIT, EDIT_REVIEW_BUTTON));
    }

    public static void trackSummarySubmit(Review review) {
        track(review, makeStrap(BaseAnalytics.SUBMIT, REVIEWS_SUBMIT, SUBMIT_REVIEW_BUTTON));
    }
}
