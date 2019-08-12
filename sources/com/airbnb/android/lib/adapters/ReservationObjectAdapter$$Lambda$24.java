package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$24 implements OnClickListener {
    private final ReservationObjectAdapter arg$1;
    private final Reservation arg$2;
    private final Listing arg$3;

    private ReservationObjectAdapter$$Lambda$24(ReservationObjectAdapter reservationObjectAdapter, Reservation reservation, Listing listing) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = reservation;
        this.arg$3 = listing;
    }

    public static OnClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, Reservation reservation, Listing listing) {
        return new ReservationObjectAdapter$$Lambda$24(reservationObjectAdapter, reservation, listing);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToWifi(this.arg$2, this.arg$3);
    }
}
