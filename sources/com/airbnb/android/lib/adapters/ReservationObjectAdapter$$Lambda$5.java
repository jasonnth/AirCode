package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$5 implements OnClickListener {
    private final ReservationObjectAdapter arg$1;
    private final TripInformationProvider arg$2;

    private ReservationObjectAdapter$$Lambda$5(ReservationObjectAdapter reservationObjectAdapter, TripInformationProvider tripInformationProvider) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = tripInformationProvider;
    }

    public static OnClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, TripInformationProvider tripInformationProvider) {
        return new ReservationObjectAdapter$$Lambda$5(reservationObjectAdapter, tripInformationProvider);
    }

    public void onClick(View view) {
        ReservationObjectAdapter.lambda$setButtonBar$4(this.arg$1, this.arg$2, view);
    }
}
