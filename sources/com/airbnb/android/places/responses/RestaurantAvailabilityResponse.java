package com.airbnb.android.places.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.RestaurantAvailability;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RestaurantAvailabilityResponse extends BaseResponse {
    @JsonProperty("restaurant_availabilities")
    private List<RestaurantAvailability> availabilities;

    public List<RestaurantAvailability> getAvailabilities() {
        return this.availabilities;
    }
}
