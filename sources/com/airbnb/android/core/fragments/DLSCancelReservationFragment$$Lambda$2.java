package com.airbnb.android.core.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DLSCancelReservationFragment$$Lambda$2 implements Action1 {
    private final DLSCancelReservationFragment arg$1;

    private DLSCancelReservationFragment$$Lambda$2(DLSCancelReservationFragment dLSCancelReservationFragment) {
        this.arg$1 = dLSCancelReservationFragment;
    }

    public static Action1 lambdaFactory$(DLSCancelReservationFragment dLSCancelReservationFragment) {
        return new DLSCancelReservationFragment$$Lambda$2(dLSCancelReservationFragment);
    }

    public void call(Object obj) {
        DLSCancelReservationFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
