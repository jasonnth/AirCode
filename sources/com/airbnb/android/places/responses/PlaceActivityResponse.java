package com.airbnb.android.places.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.PlaceActivity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceActivityResponse extends BaseResponse {
    @JsonProperty("place_activity")
    private PlaceActivity placeActivity;

    public PlaceActivity getPlaceActivity() {
        return this.placeActivity;
    }
}
