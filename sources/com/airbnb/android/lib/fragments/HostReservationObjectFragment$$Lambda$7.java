package com.airbnb.android.lib.fragments;

import android.support.p000v4.app.FragmentManager.OnBackStackChangedListener;

final /* synthetic */ class HostReservationObjectFragment$$Lambda$7 implements OnBackStackChangedListener {
    private final HostReservationObjectFragment arg$1;

    private HostReservationObjectFragment$$Lambda$7(HostReservationObjectFragment hostReservationObjectFragment) {
        this.arg$1 = hostReservationObjectFragment;
    }

    public static OnBackStackChangedListener lambdaFactory$(HostReservationObjectFragment hostReservationObjectFragment) {
        return new HostReservationObjectFragment$$Lambda$7(hostReservationObjectFragment);
    }

    public void onBackStackChanged() {
        HostReservationObjectFragment.lambda$onCreateView$5(this.arg$1);
    }
}
