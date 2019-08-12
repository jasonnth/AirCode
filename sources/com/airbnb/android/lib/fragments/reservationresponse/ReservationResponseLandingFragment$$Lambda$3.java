package com.airbnb.android.lib.fragments.reservationresponse;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReservationResponseLandingFragment$$Lambda$3 implements OnClickListener {
    private final ReservationResponseLandingFragment arg$1;

    private ReservationResponseLandingFragment$$Lambda$3(ReservationResponseLandingFragment reservationResponseLandingFragment) {
        this.arg$1 = reservationResponseLandingFragment;
    }

    public static OnClickListener lambdaFactory$(ReservationResponseLandingFragment reservationResponseLandingFragment) {
        return new ReservationResponseLandingFragment$$Lambda$3(reservationResponseLandingFragment);
    }

    public void onClick(View view) {
        this.arg$1.onAcceptReservation();
    }
}
