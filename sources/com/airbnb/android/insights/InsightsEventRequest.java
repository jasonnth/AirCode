package com.airbnb.android.insights;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.PriceFactor;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;

public class InsightsEventRequest extends BaseRequestV2<Object> {
    public static final int ACTION_CONVERSION = 2;
    public static final int ACTION_DISMISS = 3;
    public static final int ACTION_IMPLICIT_CONVERSION = 7;
    public static final int ACTION_IMPRESSION = 1;
    public static final int ACTION_PREVIEW = 6;
    public static final int ACTION_SKIP = 4;
    public static final int ACTION_UNDO = 5;
    /* access modifiers changed from: private */
    public final int actionType;
    /* access modifiers changed from: private */
    public final boolean isLastCard;
    /* access modifiers changed from: private */
    public int manualMinValue;
    /* access modifiers changed from: private */
    public PriceFactor manualWeeklyDiscount;
    /* access modifiers changed from: private */
    public final Insight story;
    /* access modifiers changed from: private */
    public final long userId;

    private class Body {
        @JsonProperty
        final int actionType;
        @JsonProperty
        final int backendPosition;
        @JsonProperty
        final int globalPosition;
        @JsonProperty
        final long listingId;
        @JsonProperty
        final int manualMinValue;
        @JsonProperty
        final double manualWeeklyDiscount;
        @JsonProperty
        final String originalRequestId;
        @JsonProperty
        final int placement;
        @JsonProperty
        final int position;
        @JsonProperty
        final String storyId;
        @JsonProperty
        final int type;
        @JsonProperty
        final long userId;

        private Body() {
            this.listingId = InsightsEventRequest.this.story.getListingId();
            this.userId = InsightsEventRequest.this.userId;
            this.originalRequestId = InsightsEventRequest.this.story.getOriginalRequestId();
            this.storyId = InsightsEventRequest.this.isLastCard ? null : InsightsEventRequest.this.story.getStoryId();
            this.type = InsightsEventRequest.this.story.getStoryType();
            this.position = InsightsEventRequest.this.story.getPosition();
            this.globalPosition = InsightsEventRequest.this.story.getGlobalPosition();
            this.backendPosition = InsightsEventRequest.this.story.getBackendPosition();
            this.placement = 6;
            this.actionType = InsightsEventRequest.this.actionType;
            this.manualMinValue = InsightsEventRequest.this.manualMinValue;
            this.manualWeeklyDiscount = InsightsEventRequest.this.manualWeeklyDiscount != null ? InsightsEventRequest.this.manualWeeklyDiscount.getFactorValue().doubleValue() : 0.0d;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StoryActionType {
    }

    public static InsightsEventRequest forTracking(Insight story2, int actionType2, boolean isLastCard2, long userId2) {
        return new InsightsEventRequest(story2, actionType2, isLastCard2, userId2);
    }

    public static InsightsEventRequest forMinPriceImplicit(Insight story2, int manualMinValue2, long userId2) {
        InsightsEventRequest request = new InsightsEventRequest(story2, 7, false, userId2);
        request.manualMinValue = manualMinValue2;
        return request;
    }

    public static InsightsEventRequest forWeeklyDiscountImplicit(Insight story2, PriceFactor manualWeeklyDiscount2, long userId2) {
        InsightsEventRequest request = new InsightsEventRequest(story2, 7, false, userId2);
        request.manualWeeklyDiscount = manualWeeklyDiscount2;
        return request;
    }

    private InsightsEventRequest(Insight story2, int actionType2, boolean isLastCard2, long userId2) {
        this.story = story2;
        this.actionType = actionType2;
        this.isLastCard = isLastCard2;
        this.userId = userId2;
    }

    public String getPath() {
        return "story_actions";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return new Body();
    }

    public Type successResponseType() {
        return Object.class;
    }
}
