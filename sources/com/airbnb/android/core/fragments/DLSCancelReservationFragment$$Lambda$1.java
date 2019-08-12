package com.airbnb.android.core.fragments;

import com.airbnb.android.core.responses.CancelReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DLSCancelReservationFragment$$Lambda$1 implements Action1 {
    private final DLSCancelReservationFragment arg$1;

    private DLSCancelReservationFragment$$Lambda$1(DLSCancelReservationFragment dLSCancelReservationFragment) {
        this.arg$1 = dLSCancelReservationFragment;
    }

    public static Action1 lambdaFactory$(DLSCancelReservationFragment dLSCancelReservationFragment) {
        return new DLSCancelReservationFragment$$Lambda$1(dLSCancelReservationFragment);
    }

    public void call(Object obj) {
        DLSCancelReservationFragment.lambda$new$1(this.arg$1, (CancelReservationResponse) obj);
    }
}
