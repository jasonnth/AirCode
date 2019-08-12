package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishlistedListing;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WishlistedListingResponse extends BaseResponse {
    @JsonProperty("wishlisted_listing")
    public WishlistedListing wishListedListing;
}
