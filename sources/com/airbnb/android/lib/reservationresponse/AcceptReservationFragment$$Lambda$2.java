package com.airbnb.android.lib.reservationresponse;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AcceptReservationFragment$$Lambda$2 implements Action1 {
    private final AcceptReservationFragment arg$1;

    private AcceptReservationFragment$$Lambda$2(AcceptReservationFragment acceptReservationFragment) {
        this.arg$1 = acceptReservationFragment;
    }

    public static Action1 lambdaFactory$(AcceptReservationFragment acceptReservationFragment) {
        return new AcceptReservationFragment$$Lambda$2(acceptReservationFragment);
    }

    public void call(Object obj) {
        AcceptReservationFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
