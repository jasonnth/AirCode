package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.ReservationCancellationInfoResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationCancellationActivity$$Lambda$1 implements Action1 {
    private final ReservationCancellationActivity arg$1;

    private ReservationCancellationActivity$$Lambda$1(ReservationCancellationActivity reservationCancellationActivity) {
        this.arg$1 = reservationCancellationActivity;
    }

    public static Action1 lambdaFactory$(ReservationCancellationActivity reservationCancellationActivity) {
        return new ReservationCancellationActivity$$Lambda$1(reservationCancellationActivity);
    }

    public void call(Object obj) {
        ReservationCancellationActivity.lambda$new$0(this.arg$1, (ReservationCancellationInfoResponse) obj);
    }
}
