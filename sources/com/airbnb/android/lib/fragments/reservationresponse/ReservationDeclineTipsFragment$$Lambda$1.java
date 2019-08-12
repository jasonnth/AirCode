package com.airbnb.android.lib.fragments.reservationresponse;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReservationDeclineTipsFragment$$Lambda$1 implements OnClickListener {
    private final ReservationDeclineTipsFragment arg$1;

    private ReservationDeclineTipsFragment$$Lambda$1(ReservationDeclineTipsFragment reservationDeclineTipsFragment) {
        this.arg$1 = reservationDeclineTipsFragment;
    }

    public static OnClickListener lambdaFactory$(ReservationDeclineTipsFragment reservationDeclineTipsFragment) {
        return new ReservationDeclineTipsFragment$$Lambda$1(reservationDeclineTipsFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().getSupportFragmentManager().popBackStack();
    }
}
