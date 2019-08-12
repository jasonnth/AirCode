package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationCancellationActivity$$Lambda$2 implements Action1 {
    private final ReservationCancellationActivity arg$1;

    private ReservationCancellationActivity$$Lambda$2(ReservationCancellationActivity reservationCancellationActivity) {
        this.arg$1 = reservationCancellationActivity;
    }

    public static Action1 lambdaFactory$(ReservationCancellationActivity reservationCancellationActivity) {
        return new ReservationCancellationActivity$$Lambda$2(reservationCancellationActivity);
    }

    public void call(Object obj) {
        ReservationCancellationActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
