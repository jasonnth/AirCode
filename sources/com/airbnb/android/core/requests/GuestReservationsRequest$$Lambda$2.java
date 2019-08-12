package com.airbnb.android.core.requests;

import com.airbnb.android.core.models.Reservation;
import com.google.common.base.Predicate;

final /* synthetic */ class GuestReservationsRequest$$Lambda$2 implements Predicate {
    private static final GuestReservationsRequest$$Lambda$2 instance = new GuestReservationsRequest$$Lambda$2();

    private GuestReservationsRequest$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return GuestReservationsRequest.lambda$checkForActiveTrip$1((Reservation) obj);
    }
}
