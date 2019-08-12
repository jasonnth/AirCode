package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HostRatingBreakdown;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HostRatingBreakdownResponse extends BaseResponse {
    @JsonProperty("listing")
    public HostRatingBreakdown ratingBreakdown;
}
