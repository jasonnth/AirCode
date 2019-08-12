package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationCanceledFragment$$Lambda$1 implements Action1 {
    private final ReservationCanceledFragment arg$1;

    private ReservationCanceledFragment$$Lambda$1(ReservationCanceledFragment reservationCanceledFragment) {
        this.arg$1 = reservationCanceledFragment;
    }

    public static Action1 lambdaFactory$(ReservationCanceledFragment reservationCanceledFragment) {
        return new ReservationCanceledFragment$$Lambda$1(reservationCanceledFragment);
    }

    public void call(Object obj) {
        ReservationCanceledFragment.lambda$new$0(this.arg$1, (ReservationResponse) obj);
    }
}
