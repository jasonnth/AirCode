package com.airbnb.android.wishlists.requests;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishListedPlace;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WishListedPlacesResponse extends BaseResponse {
    @JsonProperty("collection_places")
    public List<WishListedPlace> wishListedPlaces;
}
