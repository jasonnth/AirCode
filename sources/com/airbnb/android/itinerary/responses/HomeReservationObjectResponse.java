package com.airbnb.android.itinerary.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HomeReservationObjectResponse extends BaseResponse {
    @JsonProperty("reservation")
    public Object reservation;
}
