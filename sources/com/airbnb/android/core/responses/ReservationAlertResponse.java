package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ReservationAlert;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReservationAlertResponse extends BaseResponse {
    @JsonProperty("reservation_alerts")
    public List<ReservationAlert> reservationAlerts;
}
