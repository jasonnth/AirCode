package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Listing;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CohostedListingsResponse extends BaseResponse {
    @JsonProperty("listings")
    public List<Listing> listings;
}
