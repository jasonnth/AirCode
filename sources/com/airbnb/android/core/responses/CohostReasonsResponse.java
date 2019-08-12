package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingManager;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CohostReasonsResponse extends BaseResponse {
    @JsonProperty("listing_manager")
    public ListingManager listingManager;
}
