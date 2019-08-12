package com.airbnb.android.lib.services;

import com.airbnb.android.core.responses.GuestReservationsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class TripsReservationsSyncService$$Lambda$1 implements Action1 {
    private final TripsReservationsSyncService arg$1;

    private TripsReservationsSyncService$$Lambda$1(TripsReservationsSyncService tripsReservationsSyncService) {
        this.arg$1 = tripsReservationsSyncService;
    }

    public static Action1 lambdaFactory$(TripsReservationsSyncService tripsReservationsSyncService) {
        return new TripsReservationsSyncService$$Lambda$1(tripsReservationsSyncService);
    }

    public void call(Object obj) {
        this.arg$1.fetchReservationsList(((GuestReservationsResponse) obj).reservations);
    }
}
