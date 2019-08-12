package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.LocalAttractionsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DLSReservationObjectFragment$$Lambda$6 implements Action1 {
    private final DLSReservationObjectFragment arg$1;

    private DLSReservationObjectFragment$$Lambda$6(DLSReservationObjectFragment dLSReservationObjectFragment) {
        this.arg$1 = dLSReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(DLSReservationObjectFragment dLSReservationObjectFragment) {
        return new DLSReservationObjectFragment$$Lambda$6(dLSReservationObjectFragment);
    }

    public void call(Object obj) {
        DLSReservationObjectFragment.lambda$new$3(this.arg$1, (LocalAttractionsResponse) obj);
    }
}
