package com.airbnb.android.lib.activities;

import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentListener;

final /* synthetic */ class ReservationResponseActivity$$Lambda$5 implements EditTextFragmentListener {
    private final ReservationResponseActivity arg$1;

    private ReservationResponseActivity$$Lambda$5(ReservationResponseActivity reservationResponseActivity) {
        this.arg$1 = reservationResponseActivity;
    }

    public static EditTextFragmentListener lambdaFactory$(ReservationResponseActivity reservationResponseActivity) {
        return new ReservationResponseActivity$$Lambda$5(reservationResponseActivity);
    }

    public void onMessageSaved(String str) {
        ReservationResponseActivity.lambda$new$4(this.arg$1, str);
    }
}
