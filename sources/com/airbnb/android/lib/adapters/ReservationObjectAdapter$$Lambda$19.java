package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Reservation;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$19 implements OnClickListener {
    private final ReservationObjectAdapter arg$1;
    private final Reservation arg$2;

    private ReservationObjectAdapter$$Lambda$19(ReservationObjectAdapter reservationObjectAdapter, Reservation reservation) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = reservation;
    }

    public static OnClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, Reservation reservation) {
        return new ReservationObjectAdapter$$Lambda$19(reservationObjectAdapter, reservation);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToCancel(this.arg$2);
    }
}
