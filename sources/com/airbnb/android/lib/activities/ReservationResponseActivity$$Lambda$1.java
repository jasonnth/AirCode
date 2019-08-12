package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationResponseActivity$$Lambda$1 implements Action1 {
    private final ReservationResponseActivity arg$1;

    private ReservationResponseActivity$$Lambda$1(ReservationResponseActivity reservationResponseActivity) {
        this.arg$1 = reservationResponseActivity;
    }

    public static Action1 lambdaFactory$(ReservationResponseActivity reservationResponseActivity) {
        return new ReservationResponseActivity$$Lambda$1(reservationResponseActivity);
    }

    public void call(Object obj) {
        ReservationResponseActivity.lambda$new$0(this.arg$1, (ReservationResponse) obj);
    }
}
