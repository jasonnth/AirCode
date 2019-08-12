package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.responses.ReviewResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateReviewRequest extends BaseRequestV2<ReviewResponse> {
    public static final String KEY_ACCURACY = "accuracy";
    public static final String KEY_CHECKIN = "checkin";
    public static final String KEY_CLEANLINESS = "cleanliness";
    public static final String KEY_COMMUNICATION = "communication";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_OVERALL = "rating";
    public static final String KEY_PRIVATE_FEEDBACK = "private_feedback";
    public static final String KEY_PUBLIC_FEEDBACK = "comments";
    public static final String KEY_RECOMMEND = "recommend";
    public static final String KEY_RESPECT_HOUSE_RULES = "respect_house_rules";
    public static final String KEY_SUBMITTED = "has_submitted";
    public static final String KEY_VALUE = "value";
    private final Object requestBody;
    private final long reviewId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReviewKeys {
    }

    private static class SubmitGuestReview extends SubmitReview {
        @JsonProperty("accuracy")
        final int accuracy;
        @JsonProperty("checkin")
        final int checkin;
        @JsonProperty("location")
        final int location;
        @JsonProperty("value")
        final int value;

        SubmitGuestReview(Review review) {
            super(review);
            this.accuracy = review.getAccuracyRating().intValue();
            this.checkin = review.getCheckinRating().intValue();
            this.location = review.getLocationRating().intValue();
            this.value = review.getValueRating().intValue();
        }
    }

    private static class SubmitHostReview extends SubmitReview {
        @JsonProperty("respect_house_rules")
        final int respectHouseRules;

        SubmitHostReview(Review review) {
            super(review);
            this.respectHouseRules = review.getRespectHouseRulesRating().intValue();
        }
    }

    static abstract class SubmitReview {
        @JsonProperty("cleanliness")
        final int cleanliness;
        @JsonProperty("communication")
        final int communication;
        @JsonProperty("rating")
        final int overall;
        @JsonProperty("private_feedback")
        final String privateFeedback;
        @JsonProperty("comments")
        final String publicFeedback;
        @JsonProperty("recommend")
        final boolean recommend;
        @JsonProperty("has_submitted")
        final boolean submitted = true;

        SubmitReview(Review review) {
            this.publicFeedback = review.getPublicFeedback();
            this.privateFeedback = review.getPrivateFeedback();
            this.overall = review.getAverageRating().intValue();
            this.cleanliness = review.getCleanlinessRating().intValue();
            this.communication = review.getCommunicationRating().intValue();
            this.recommend = review.isRecommended().booleanValue();
        }
    }

    public static UpdateReviewRequest forSubmit(Review review) {
        return new UpdateReviewRequest(review.getId(), review.isGuestReviewingHost() ? new SubmitGuestReview(review) : new SubmitHostReview(review));
    }

    public static UpdateReviewRequest forSaveProgress(Review review, String key, Object value) {
        return new UpdateReviewRequest(review.getId(), createBodyForSaveProgress(key, value));
    }

    private UpdateReviewRequest(long reviewId2, Object requestBody2) {
        this.reviewId = reviewId2;
        this.requestBody = requestBody2;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public String getPath() {
        return "reviews/" + this.reviewId;
    }

    public Object getBody() {
        return this.requestBody;
    }

    private static String createBodyForSaveProgress(String key, Object value) {
        try {
            return new JSONObject().put(key, value).toString();
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
            return "";
        }
    }

    public Type successResponseType() {
        return ReviewResponse.class;
    }
}
