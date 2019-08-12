package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class HostReservationObjectFragment$$Lambda$6 implements Action1 {
    private final HostReservationObjectFragment arg$1;

    private HostReservationObjectFragment$$Lambda$6(HostReservationObjectFragment hostReservationObjectFragment) {
        this.arg$1 = hostReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(HostReservationObjectFragment hostReservationObjectFragment) {
        return new HostReservationObjectFragment$$Lambda$6(hostReservationObjectFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleError((AirRequestNetworkException) obj);
    }
}
