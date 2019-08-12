package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.utils.Strap;

public class ReviewsAnalytics extends BaseAnalytics {
    private static final String ACTION_DISMISS = "dismiss";
    private static final String ACTION_EDIT_FEEDBACK = "edit_review_click";
    private static final String ACTION_EDIT_RATING = "edit_rating_click";
    private static final String ACTION_EDIT_RECOMMEND = "edit_would_recommend_click";
    private static final String ACTION_IMPRESSION = "impression";
    private static final String ACTION_SUBMIT = "submit";
    private static final String ACTION_SUBMIT_REVIEW = "submit_review_click";
    private static final String ACTION_VIEW_REVIEW = "view_review";
    private static final String EVENT_REVIEWS = "reviews";
    private static final String FIELD_ACTION = "action";
    private static final String FIELD_CONFIRMATION_CODE = "confirmation_code";
    private static final String FIELD_PAGE = "page";
    private static final String FIELD_REVIEW_ID = "review_id";
    private static final String FIELD_ROLE = "role";
    private static final String PAGE_DOUBLE_BLIND_FTUE = "double_blind_ftue";
    private static final String PAGE_DOUBLE_BLIND_HIDDEN_DIALOG = "double_blind_hidden_dialog";
    private static final String PAGE_DOUBLE_BLIND_VISIBLE_DIALOG = "double_blind_visible_dialog";
    private static final String PAGE_FEEDBACK = "review_feedback";
    private static final String PAGE_NPS = "nps";
    private static final String PAGE_RATINGS = "review_rating";
    private static final String PAGE_SUMMARY = "submit_review";
    private static final String ROLE_GUEST = "guest";
    private static final String ROLE_HOST = "host";

    public static void trackFeedbackSection(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_FEEDBACK, "impression"));
    }

    public static void trackRatingSection(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_RATINGS, "impression"));
    }

    public static void trackSummarySection(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_SUMMARY, "impression"));
    }

    public static void trackEditFeedback(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_SUMMARY, ACTION_EDIT_FEEDBACK));
    }

    public static void trackEditRating(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_SUMMARY, ACTION_EDIT_RATING));
    }

    public static void trackEditRecommend(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_SUMMARY, ACTION_EDIT_RECOMMEND));
    }

    public static void trackSubmitReview(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_SUMMARY, ACTION_SUBMIT_REVIEW));
    }

    public static void trackSubmitNPS(Review review, int score) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_NPS, "submit").mo11637kv("score", score));
    }

    public static void trackDoubleBlindFtue(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_DOUBLE_BLIND_FTUE, "impression"));
    }

    public static void trackPostDoubleBlindHiddenDialog(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_DOUBLE_BLIND_HIDDEN_DIALOG, "impression"));
    }

    public static void trackPostDoubleBlindVisibleDialog(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_DOUBLE_BLIND_VISIBLE_DIALOG, "impression"));
    }

    public static void trackDismissPostDoubleBlindDialog(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_DOUBLE_BLIND_VISIBLE_DIALOG, "dismiss"));
    }

    public static void trackViewReview(Review review) {
        AirbnbEventLogger.track("reviews", makeReviewStrap(review, PAGE_DOUBLE_BLIND_VISIBLE_DIALOG, ACTION_VIEW_REVIEW));
    }

    private static Strap makeReviewStrap(Review review, String page, String action) {
        String confirmationCode;
        if (review.getReservation() == null) {
            confirmationCode = "";
        } else {
            confirmationCode = review.getReservation().getConfirmationCode();
        }
        return new Strap().mo11639kv("page", page).mo11639kv("action", action).mo11639kv(FIELD_ROLE, review.isGuestReviewingHost() ? "guest" : "host").mo11639kv("confirmation_code", confirmationCode).mo11638kv(FIELD_REVIEW_ID, review.getId());
    }
}
