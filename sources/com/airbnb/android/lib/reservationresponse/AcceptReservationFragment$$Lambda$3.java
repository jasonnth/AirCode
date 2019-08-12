package com.airbnb.android.lib.reservationresponse;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AcceptReservationFragment$$Lambda$3 implements OnClickListener {
    private final AcceptReservationFragment arg$1;

    private AcceptReservationFragment$$Lambda$3(AcceptReservationFragment acceptReservationFragment) {
        this.arg$1 = acceptReservationFragment;
    }

    public static OnClickListener lambdaFactory$(AcceptReservationFragment acceptReservationFragment) {
        return new AcceptReservationFragment$$Lambda$3(acceptReservationFragment);
    }

    public void onClick(View view) {
        this.arg$1.navigateBack();
    }
}
