package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationResponseActivity$$Lambda$4 implements Action1 {
    private final ReservationResponseActivity arg$1;

    private ReservationResponseActivity$$Lambda$4(ReservationResponseActivity reservationResponseActivity) {
        this.arg$1 = reservationResponseActivity;
    }

    public static Action1 lambdaFactory$(ReservationResponseActivity reservationResponseActivity) {
        return new ReservationResponseActivity$$Lambda$4(reservationResponseActivity);
    }

    public void call(Object obj) {
        ReservationResponseActivity.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
