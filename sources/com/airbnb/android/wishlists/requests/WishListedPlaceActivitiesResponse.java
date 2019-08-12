package com.airbnb.android.wishlists.requests;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishListedPlaceActivity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WishListedPlaceActivitiesResponse extends BaseResponse {
    @JsonProperty("collection_activities")
    public List<WishListedPlaceActivity> wishListedPlaceActivities;
}
