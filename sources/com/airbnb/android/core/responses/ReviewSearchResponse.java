package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ReviewSearchResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReviewSearchResponse extends BaseResponse {
    @JsonProperty("review_searches")
    public List<ReviewSearchResult> results;
}
