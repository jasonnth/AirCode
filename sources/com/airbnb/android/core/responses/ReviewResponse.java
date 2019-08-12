package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Review;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewResponse extends BaseResponse {
    @JsonProperty("review")
    public Review review;
}
