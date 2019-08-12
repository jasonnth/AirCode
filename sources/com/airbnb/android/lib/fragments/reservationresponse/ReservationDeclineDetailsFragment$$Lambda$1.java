package com.airbnb.android.lib.fragments.reservationresponse;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.MessageType;

final /* synthetic */ class ReservationDeclineDetailsFragment$$Lambda$1 implements OnClickListener {
    private final ReservationDeclineDetailsFragment arg$1;
    private final MessageType arg$2;

    private ReservationDeclineDetailsFragment$$Lambda$1(ReservationDeclineDetailsFragment reservationDeclineDetailsFragment, MessageType messageType) {
        this.arg$1 = reservationDeclineDetailsFragment;
        this.arg$2 = messageType;
    }

    public static OnClickListener lambdaFactory$(ReservationDeclineDetailsFragment reservationDeclineDetailsFragment, MessageType messageType) {
        return new ReservationDeclineDetailsFragment$$Lambda$1(reservationDeclineDetailsFragment, messageType);
    }

    public void onClick(View view) {
        this.arg$1.getNavigator().showEditTextFragment(this.arg$2);
    }
}
