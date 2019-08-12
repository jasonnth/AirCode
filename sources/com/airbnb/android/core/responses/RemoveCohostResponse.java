package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.ListingManager;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveCohostResponse {
    @JsonProperty("listing_manager")
    public ListingManager listingManager;
}
