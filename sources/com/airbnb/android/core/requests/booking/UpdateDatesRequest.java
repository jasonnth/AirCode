package com.airbnb.android.core.requests.booking;

import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.requests.booking.requestbodies.ReservationRequestBody.Builder;

public class UpdateDatesRequest extends UpdateReservationRequest {
    private final ReservationDetails reservationDetails;

    public UpdateDatesRequest(ReservationDetails details) {
        this.reservationDetails = details;
        this.reservationId = details.reservationId();
        this.useForP4Checkout = FeatureToggles.useForP4CheckoutFormat();
    }

    public Object getBody() {
        return new Builder().checkIn(this.reservationDetails.checkIn().getIsoDateString()).checkOut(this.reservationDetails.checkOut().getIsoDateString()).build();
    }
}
