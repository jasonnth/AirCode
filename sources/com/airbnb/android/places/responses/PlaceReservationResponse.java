package com.airbnb.android.places.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.PlaceReservation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceReservationResponse extends BaseResponse {
    @JsonProperty("place_reservation")
    private PlaceReservation placeReservation;

    public PlaceReservation getPlaceReservation() {
        return this.placeReservation;
    }
}
