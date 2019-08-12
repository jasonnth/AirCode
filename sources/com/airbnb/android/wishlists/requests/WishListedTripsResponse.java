package com.airbnb.android.wishlists.requests;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishListedTrip;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WishListedTripsResponse extends BaseResponse {
    @JsonProperty("collection_mt_templates")
    public List<WishListedTrip> wishListedTrips;
}
