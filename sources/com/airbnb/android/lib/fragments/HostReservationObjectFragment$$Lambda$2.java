package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class HostReservationObjectFragment$$Lambda$2 implements Action1 {
    private final HostReservationObjectFragment arg$1;

    private HostReservationObjectFragment$$Lambda$2(HostReservationObjectFragment hostReservationObjectFragment) {
        this.arg$1 = hostReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(HostReservationObjectFragment hostReservationObjectFragment) {
        return new HostReservationObjectFragment$$Lambda$2(hostReservationObjectFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.recyclerView, (AirRequestNetworkException) obj);
    }
}
