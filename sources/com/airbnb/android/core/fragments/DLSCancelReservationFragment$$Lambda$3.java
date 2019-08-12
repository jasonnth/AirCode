package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DLSCancelReservationFragment$$Lambda$3 implements OnClickListener {
    private final DLSCancelReservationFragment arg$1;

    private DLSCancelReservationFragment$$Lambda$3(DLSCancelReservationFragment dLSCancelReservationFragment) {
        this.arg$1 = dLSCancelReservationFragment;
    }

    public static OnClickListener lambdaFactory$(DLSCancelReservationFragment dLSCancelReservationFragment) {
        return new DLSCancelReservationFragment$$Lambda$3(dLSCancelReservationFragment);
    }

    public void onClick(View view) {
        this.arg$1.setResultOkAndFinish();
    }
}
