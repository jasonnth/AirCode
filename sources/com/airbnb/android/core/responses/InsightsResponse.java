package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Insight;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class InsightsResponse extends BaseResponse {
    @JsonProperty("stories")
    public List<Insight> stories;
}
