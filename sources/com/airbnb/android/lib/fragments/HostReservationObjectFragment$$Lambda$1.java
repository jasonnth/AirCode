package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostReservationObjectFragment$$Lambda$1 implements Action1 {
    private final HostReservationObjectFragment arg$1;

    private HostReservationObjectFragment$$Lambda$1(HostReservationObjectFragment hostReservationObjectFragment) {
        this.arg$1 = hostReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(HostReservationObjectFragment hostReservationObjectFragment) {
        return new HostReservationObjectFragment$$Lambda$1(hostReservationObjectFragment);
    }

    public void call(Object obj) {
        HostReservationObjectFragment.lambda$new$0(this.arg$1, (AirBatchResponse) obj);
    }
}
