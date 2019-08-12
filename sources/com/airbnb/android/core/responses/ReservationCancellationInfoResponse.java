package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationCancellationInfoResponse {
    @JsonProperty("reservation_cancellation_info")
    public ReservationCancellationInfo reservationCancellationInfo;
}
