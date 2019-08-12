package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationObjectDeepLinkActivity$$Lambda$1 implements Action1 {
    private final ReservationObjectDeepLinkActivity arg$1;

    private ReservationObjectDeepLinkActivity$$Lambda$1(ReservationObjectDeepLinkActivity reservationObjectDeepLinkActivity) {
        this.arg$1 = reservationObjectDeepLinkActivity;
    }

    public static Action1 lambdaFactory$(ReservationObjectDeepLinkActivity reservationObjectDeepLinkActivity) {
        return new ReservationObjectDeepLinkActivity$$Lambda$1(reservationObjectDeepLinkActivity);
    }

    public void call(Object obj) {
        this.arg$1.handleReservation(((ReservationResponse) obj).reservation);
    }
}
