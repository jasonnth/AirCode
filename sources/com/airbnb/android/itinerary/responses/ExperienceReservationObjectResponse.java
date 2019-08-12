package com.airbnb.android.itinerary.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExperienceReservationObjectResponse extends BaseResponse {
    @JsonProperty("experience_reservation")
    public Object reservation;
}
