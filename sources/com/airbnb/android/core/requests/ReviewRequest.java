package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ReviewResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ReviewRequest extends BaseRequestV2<ReviewResponse> {
    private final long reviewId;

    public ReviewRequest(long reviewId2) {
        this.reviewId = reviewId2;
    }

    public String getPath() {
        return "reviews/" + this.reviewId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "leave_review");
    }

    public Type successResponseType() {
        return ReviewResponse.class;
    }
}
