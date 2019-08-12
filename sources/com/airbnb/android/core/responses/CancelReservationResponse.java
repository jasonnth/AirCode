package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelReservationResponse extends BaseResponse {
    @JsonProperty("reservation")
    public Reservation reservation;
}
