package com.airbnb.android.lib.fragments;

import com.airbnb.android.lib.tripassistant.HelpThreadsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DLSReservationObjectFragment$$Lambda$5 implements Action1 {
    private final DLSReservationObjectFragment arg$1;

    private DLSReservationObjectFragment$$Lambda$5(DLSReservationObjectFragment dLSReservationObjectFragment) {
        this.arg$1 = dLSReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(DLSReservationObjectFragment dLSReservationObjectFragment) {
        return new DLSReservationObjectFragment$$Lambda$5(dLSReservationObjectFragment);
    }

    public void call(Object obj) {
        DLSReservationObjectFragment.lambda$new$2(this.arg$1, (HelpThreadsResponse) obj);
    }
}
