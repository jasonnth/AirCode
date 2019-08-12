package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DLSReservationObjectFragment$$Lambda$4 implements Action1 {
    private final DLSReservationObjectFragment arg$1;

    private DLSReservationObjectFragment$$Lambda$4(DLSReservationObjectFragment dLSReservationObjectFragment) {
        this.arg$1 = dLSReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(DLSReservationObjectFragment dLSReservationObjectFragment) {
        return new DLSReservationObjectFragment$$Lambda$4(dLSReservationObjectFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleErrorResponse((AirRequestNetworkException) obj);
    }
}
