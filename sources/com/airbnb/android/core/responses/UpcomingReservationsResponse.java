package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.AirRequestFactory.Provider;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UpcomingReservationsResponse extends BaseResponse implements Provider<Reservation> {
    @JsonProperty("reservations")
    public List<Reservation> reservations;

    public List<Reservation> provide() {
        return this.reservations;
    }
}
