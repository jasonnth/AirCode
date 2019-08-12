package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ReviewKeyword;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReviewKeywordsResponse extends BaseResponse {
    @JsonProperty("review_keywords")
    public List<ReviewKeyword> keywords;
}
