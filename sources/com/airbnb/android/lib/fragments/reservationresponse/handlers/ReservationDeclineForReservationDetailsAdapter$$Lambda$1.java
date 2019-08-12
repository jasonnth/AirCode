package com.airbnb.android.lib.fragments.reservationresponse.handlers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.MessageType;

final /* synthetic */ class ReservationDeclineForReservationDetailsAdapter$$Lambda$1 implements OnClickListener {
    private final ReservationResponseActivity arg$1;

    private ReservationDeclineForReservationDetailsAdapter$$Lambda$1(ReservationResponseActivity reservationResponseActivity) {
        this.arg$1 = reservationResponseActivity;
    }

    public static OnClickListener lambdaFactory$(ReservationResponseActivity reservationResponseActivity) {
        return new ReservationDeclineForReservationDetailsAdapter$$Lambda$1(reservationResponseActivity);
    }

    public void onClick(View view) {
        this.arg$1.showEditTextFragment(MessageType.MessageToGuest);
    }
}
