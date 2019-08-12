package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostReservationObjectFragment$$Lambda$3 implements Action1 {
    private final HostReservationObjectFragment arg$1;

    private HostReservationObjectFragment$$Lambda$3(HostReservationObjectFragment hostReservationObjectFragment) {
        this.arg$1 = hostReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(HostReservationObjectFragment hostReservationObjectFragment) {
        return new HostReservationObjectFragment$$Lambda$3(hostReservationObjectFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleLoad(TripInformationProvider.create(((ReservationResponse) obj).reservation));
    }
}
