package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.InquiryResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostReservationObjectFragment$$Lambda$5 implements Action1 {
    private final HostReservationObjectFragment arg$1;

    private HostReservationObjectFragment$$Lambda$5(HostReservationObjectFragment hostReservationObjectFragment) {
        this.arg$1 = hostReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(HostReservationObjectFragment hostReservationObjectFragment) {
        return new HostReservationObjectFragment$$Lambda$5(hostReservationObjectFragment);
    }

    public void call(Object obj) {
        HostReservationObjectFragment.lambda$new$4(this.arg$1, (InquiryResponse) obj);
    }
}
