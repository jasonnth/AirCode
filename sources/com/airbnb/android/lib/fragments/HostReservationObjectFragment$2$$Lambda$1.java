package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostReservationObjectFragment$2$$Lambda$1 implements OnClickListener {
    private final C68162 arg$1;

    private HostReservationObjectFragment$2$$Lambda$1(C68162 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C68162 r1) {
        return new HostReservationObjectFragment$2$$Lambda$1(r1);
    }

    public void onClick(View view) {
        HostReservationObjectFragment.this.loadCalendar();
    }
}
