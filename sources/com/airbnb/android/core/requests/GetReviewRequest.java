package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.android.core.responses.ReviewResponse;
import java.lang.reflect.Type;
import java.util.Locale;

public class GetReviewRequest extends BaseRequest<ReviewResponse> {
    private final long reviewId;

    public GetReviewRequest(long reviewId2, BaseRequestListener<ReviewResponse> listener) {
        withListener(listener);
        this.reviewId = reviewId2;
    }

    public String getPath() {
        return String.format(Locale.US, "reviews/%d", new Object[]{Long.valueOf(this.reviewId)});
    }

    public Type successResponseType() {
        return ReviewResponse.class;
    }
}
