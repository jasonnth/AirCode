package com.airbnb.android.core.requests.booking;

import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.requests.booking.requestbodies.ReservationRequestBody.Builder;

public class UpdateBusinessTravelDetailRequest extends UpdateReservationRequest {
    private final ReservationDetails reservationDetails;

    public static UpdateBusinessTravelDetailRequest forReservationDetails(ReservationDetails details) {
        return new UpdateBusinessTravelDetailRequest(details);
    }

    private UpdateBusinessTravelDetailRequest(ReservationDetails details) {
        this.reservationDetails = details;
        this.reservationId = details.reservationId();
        this.useForP4Checkout = FeatureToggles.useForP4CheckoutFormat();
    }

    public Object getBody() {
        return new Builder().isAirbnbCreditExcluded(Boolean.valueOf(this.reservationDetails.isVerifiedBusinessTrip())).businessVatRateApplied(Boolean.valueOf(this.reservationDetails.isVerifiedBusinessTrip())).build();
    }
}
