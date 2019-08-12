package com.airbnb.android.lib.fragments.reservationresponse;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationResponseLandingFragment$$Lambda$2 implements Action1 {
    private final ReservationResponseLandingFragment arg$1;

    private ReservationResponseLandingFragment$$Lambda$2(ReservationResponseLandingFragment reservationResponseLandingFragment) {
        this.arg$1 = reservationResponseLandingFragment;
    }

    public static Action1 lambdaFactory$(ReservationResponseLandingFragment reservationResponseLandingFragment) {
        return new ReservationResponseLandingFragment$$Lambda$2(reservationResponseLandingFragment);
    }

    public void call(Object obj) {
        ReservationResponseLandingFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
