package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.responses.ReviewResponse;
import com.airbnb.android.core.utils.BuildHelper;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class SubmitReviewRequest extends BaseRequest<ReviewResponse> {
    private final Review review;

    public SubmitReviewRequest(Review review2, BaseRequestListener<ReviewResponse> listener) {
        withListener(listener);
        this.review = review2;
    }

    public String getPath() {
        return "reviews/" + Long.toString(this.review.getId()) + "/update";
    }

    public String getBody() {
        JSONObject reviewJson = new JSONObject();
        try {
            reviewJson.putOpt(UpdateReviewRequest.KEY_PUBLIC_FEEDBACK, this.review.getPublicFeedback()).putOpt(UpdateReviewRequest.KEY_PRIVATE_FEEDBACK, this.review.getCombinedPrivateFeedback()).putOpt(UpdateReviewRequest.KEY_RECOMMEND, this.review.isRecommended()).putOpt(UpdateReviewRequest.KEY_COMMUNICATION, this.review.getCommunicationRating()).putOpt(UpdateReviewRequest.KEY_CLEANLINESS, this.review.getCleanlinessRating()).putOpt(UpdateReviewRequest.KEY_ACCURACY, this.review.getAccuracyRating()).putOpt(UpdateReviewRequest.KEY_CHECKIN, this.review.getCheckinRating()).putOpt("location", this.review.getLocationRating()).putOpt(UpdateReviewRequest.KEY_RESPECT_HOUSE_RULES, this.review.getRespectHouseRulesRating()).putOpt("value", this.review.getValueRating()).putOpt(UpdateReviewRequest.KEY_OVERALL, this.review.getAverageRating());
        } catch (JSONException e) {
            BuildHelper.debugErrorLog(SubmitReviewRequest.class.getSimpleName(), "Error constructing JSON", e);
        }
        return reviewJson.toString();
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return ReviewResponse.class;
    }
}
