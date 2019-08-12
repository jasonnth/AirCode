package com.airbnb.android.itinerary.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceReservationObjectResponse extends BaseResponse {
    @JsonProperty("place_reservation")
    public Object reservation;
}
