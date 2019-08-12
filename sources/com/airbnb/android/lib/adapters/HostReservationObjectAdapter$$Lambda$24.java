package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostReservationObjectAdapter$$Lambda$24 implements OnClickListener {
    private final HostReservationObjectAdapter arg$1;
    private final String arg$2;

    private HostReservationObjectAdapter$$Lambda$24(HostReservationObjectAdapter hostReservationObjectAdapter, String str) {
        this.arg$1 = hostReservationObjectAdapter;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(HostReservationObjectAdapter hostReservationObjectAdapter, String str) {
        return new HostReservationObjectAdapter$$Lambda$24(hostReservationObjectAdapter, str);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToResolutionCenter(this.arg$2);
    }
}
