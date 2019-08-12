package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;

final /* synthetic */ class HostReservationObjectAdapter$$Lambda$4 implements OnClickListener {
    private final HostReservationObjectAdapter arg$1;
    private final User arg$2;

    private HostReservationObjectAdapter$$Lambda$4(HostReservationObjectAdapter hostReservationObjectAdapter, User user) {
        this.arg$1 = hostReservationObjectAdapter;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(HostReservationObjectAdapter hostReservationObjectAdapter, User user) {
        return new HostReservationObjectAdapter$$Lambda$4(hostReservationObjectAdapter, user);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToGuestRatingsModal(this.arg$2);
    }
}
