package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Listing;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleListingResponse extends BaseResponse {
    @JsonProperty("listing")
    public Listing listing;
}
