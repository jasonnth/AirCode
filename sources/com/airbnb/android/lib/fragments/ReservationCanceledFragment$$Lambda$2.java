package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationCanceledFragment$$Lambda$2 implements Action1 {
    private final ReservationCanceledFragment arg$1;

    private ReservationCanceledFragment$$Lambda$2(ReservationCanceledFragment reservationCanceledFragment) {
        this.arg$1 = reservationCanceledFragment;
    }

    public static Action1 lambdaFactory$(ReservationCanceledFragment reservationCanceledFragment) {
        return new ReservationCanceledFragment$$Lambda$2(reservationCanceledFragment);
    }

    public void call(Object obj) {
        ReservationCanceledFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
