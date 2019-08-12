package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$10 implements OnClickListener {
    private final ReservationObjectAdapter arg$1;

    private ReservationObjectAdapter$$Lambda$10(ReservationObjectAdapter reservationObjectAdapter) {
        this.arg$1 = reservationObjectAdapter;
    }

    public static OnClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter) {
        return new ReservationObjectAdapter$$Lambda$10(reservationObjectAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToHelpCenter();
    }
}
