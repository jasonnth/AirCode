package com.airbnb.android.wishlists.requests;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishlistedListing;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WishlistedListingsResponse extends BaseResponse {
    @JsonProperty("wishlisted_listings")
    public List<WishlistedListing> wishListedListings;
}
