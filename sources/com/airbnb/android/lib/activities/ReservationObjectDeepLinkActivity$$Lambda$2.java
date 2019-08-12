package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationObjectDeepLinkActivity$$Lambda$2 implements Action1 {
    private final ReservationObjectDeepLinkActivity arg$1;

    private ReservationObjectDeepLinkActivity$$Lambda$2(ReservationObjectDeepLinkActivity reservationObjectDeepLinkActivity) {
        this.arg$1 = reservationObjectDeepLinkActivity;
    }

    public static Action1 lambdaFactory$(ReservationObjectDeepLinkActivity reservationObjectDeepLinkActivity) {
        return new ReservationObjectDeepLinkActivity$$Lambda$2(reservationObjectDeepLinkActivity);
    }

    public void call(Object obj) {
        this.arg$1.handleFetchError((AirRequestNetworkException) obj);
    }
}
