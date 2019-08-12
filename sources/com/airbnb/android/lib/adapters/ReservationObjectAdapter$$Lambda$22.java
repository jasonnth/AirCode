package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.Reservation;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$22 implements OnLongClickListener {
    private final ReservationObjectAdapter arg$1;
    private final Reservation arg$2;

    private ReservationObjectAdapter$$Lambda$22(ReservationObjectAdapter reservationObjectAdapter, Reservation reservation) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = reservation;
    }

    public static OnLongClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, Reservation reservation) {
        return new ReservationObjectAdapter$$Lambda$22(reservationObjectAdapter, reservation);
    }

    public boolean onLongClick(View view) {
        return this.arg$1.copyToClipboard(this.arg$2.getListing().getAddress());
    }
}
