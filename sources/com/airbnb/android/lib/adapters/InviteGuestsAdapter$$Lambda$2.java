package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ReservationUser;

final /* synthetic */ class InviteGuestsAdapter$$Lambda$2 implements OnClickListener {
    private final InviteGuestsAdapter arg$1;
    private final ReservationUser arg$2;

    private InviteGuestsAdapter$$Lambda$2(InviteGuestsAdapter inviteGuestsAdapter, ReservationUser reservationUser) {
        this.arg$1 = inviteGuestsAdapter;
        this.arg$2 = reservationUser;
    }

    public static OnClickListener lambdaFactory$(InviteGuestsAdapter inviteGuestsAdapter, ReservationUser reservationUser) {
        return new InviteGuestsAdapter$$Lambda$2(inviteGuestsAdapter, reservationUser);
    }

    public void onClick(View view) {
        this.arg$1.callback.removeGuest(this.arg$2);
    }
}
