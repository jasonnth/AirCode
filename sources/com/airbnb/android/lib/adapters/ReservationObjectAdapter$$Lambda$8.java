package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$8 implements OnClickListener {
    private final ReservationObjectAdapter arg$1;
    private final Listing arg$2;

    private ReservationObjectAdapter$$Lambda$8(ReservationObjectAdapter reservationObjectAdapter, Listing listing) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = listing;
    }

    public static OnClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, Listing listing) {
        return new ReservationObjectAdapter$$Lambda$8(reservationObjectAdapter, listing);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToListing(this.arg$2);
    }
}
