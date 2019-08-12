package com.airbnb.android.lib.services;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class TripsReservationsSyncService$$Lambda$2 implements Action1 {
    private final TripsReservationsSyncService arg$1;

    private TripsReservationsSyncService$$Lambda$2(TripsReservationsSyncService tripsReservationsSyncService) {
        this.arg$1 = tripsReservationsSyncService;
    }

    public static Action1 lambdaFactory$(TripsReservationsSyncService tripsReservationsSyncService) {
        return new TripsReservationsSyncService$$Lambda$2(tripsReservationsSyncService);
    }

    public void call(Object obj) {
        this.arg$1.fetchReservationDetails(((ReservationResponse) obj).reservation);
    }
}
