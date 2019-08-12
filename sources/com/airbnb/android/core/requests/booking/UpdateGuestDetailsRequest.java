package com.airbnb.android.core.requests.booking;

import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.requests.booking.requestbodies.ReservationRequestBody.Builder;

public class UpdateGuestDetailsRequest extends UpdateReservationRequest {
    private final ReservationDetails reservationDetails;

    public UpdateGuestDetailsRequest(ReservationDetails details) {
        this.reservationDetails = details;
        this.reservationId = details.reservationId();
        this.useForP4Checkout = FeatureToggles.useForP4CheckoutFormat();
    }

    public Object getBody() {
        return new Builder().numberOfAdults(this.reservationDetails.numberOfAdults()).numberOfChildren(this.reservationDetails.numberOfChildren()).numberOfInfants(this.reservationDetails.numberOfInfants()).isBringingPets(this.reservationDetails.isBringingPets()).build();
    }
}
