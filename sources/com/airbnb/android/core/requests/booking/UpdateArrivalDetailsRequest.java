package com.airbnb.android.core.requests.booking;

import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.requests.booking.requestbodies.ReservationRequestBody.Builder;

public class UpdateArrivalDetailsRequest extends UpdateReservationRequest {
    private final ReservationDetails reservationDetails;

    public UpdateArrivalDetailsRequest(ReservationDetails details) {
        this.reservationDetails = details;
        this.reservationId = details.reservationId();
    }

    public Object getBody() {
        return new Builder().checkInHour(this.reservationDetails.checkInHour()).build();
    }
}
