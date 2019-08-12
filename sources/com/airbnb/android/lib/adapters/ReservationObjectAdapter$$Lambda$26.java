package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.User;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$26 implements OnLongClickListener {
    private final ReservationObjectAdapter arg$1;
    private final User arg$2;

    private ReservationObjectAdapter$$Lambda$26(ReservationObjectAdapter reservationObjectAdapter, User user) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = user;
    }

    public static OnLongClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, User user) {
        return new ReservationObjectAdapter$$Lambda$26(reservationObjectAdapter, user);
    }

    public boolean onLongClick(View view) {
        return this.arg$1.copyToClipboard(this.arg$2.getEmailAddress());
    }
}
