package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.Reservation;
import com.google.common.base.Predicate;

final /* synthetic */ class GuestReservationsResponse$$Lambda$2 implements Predicate {
    private final long arg$1;

    private GuestReservationsResponse$$Lambda$2(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new GuestReservationsResponse$$Lambda$2(j);
    }

    public boolean apply(Object obj) {
        return GuestReservationsResponse.lambda$findReservationById$1(this.arg$1, (Reservation) obj);
    }
}
