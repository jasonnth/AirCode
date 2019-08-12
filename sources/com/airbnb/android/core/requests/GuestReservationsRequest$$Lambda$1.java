package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;

final /* synthetic */ class GuestReservationsRequest$$Lambda$1 implements Runnable {
    private final GuestReservationsRequest arg$1;
    private final AirResponse arg$2;

    private GuestReservationsRequest$$Lambda$1(GuestReservationsRequest guestReservationsRequest, AirResponse airResponse) {
        this.arg$1 = guestReservationsRequest;
        this.arg$2 = airResponse;
    }

    public static Runnable lambdaFactory$(GuestReservationsRequest guestReservationsRequest, AirResponse airResponse) {
        return new GuestReservationsRequest$$Lambda$1(guestReservationsRequest, airResponse);
    }

    public void run() {
        GuestReservationsRequest.lambda$transformResponse$0(this.arg$1, this.arg$2);
    }
}
