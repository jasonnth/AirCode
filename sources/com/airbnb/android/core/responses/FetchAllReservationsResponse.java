package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FetchAllReservationsResponse extends BaseResponse {
    @JsonProperty("reservations")
    public List<Reservation> reservations;
}
