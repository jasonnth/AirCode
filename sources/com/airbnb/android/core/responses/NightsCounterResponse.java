package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.NightCount;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class NightsCounterResponse extends BaseResponse {
    @JsonProperty("night_counts")
    public List<NightCount> nightCounts;
}
