package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostReservationObjectAdapter$$Lambda$19 implements OnClickListener {
    private final HostReservationObjectAdapter arg$1;

    private HostReservationObjectAdapter$$Lambda$19(HostReservationObjectAdapter hostReservationObjectAdapter) {
        this.arg$1 = hostReservationObjectAdapter;
    }

    public static OnClickListener lambdaFactory$(HostReservationObjectAdapter hostReservationObjectAdapter) {
        return new HostReservationObjectAdapter$$Lambda$19(hostReservationObjectAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToExportCalendar();
    }
}
