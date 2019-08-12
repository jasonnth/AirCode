package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.HelpThread;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$2 implements OnClickListener {
    private final ReservationObjectAdapter arg$1;
    private final HelpThread arg$2;

    private ReservationObjectAdapter$$Lambda$2(ReservationObjectAdapter reservationObjectAdapter, HelpThread helpThread) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = helpThread;
    }

    public static OnClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, HelpThread helpThread) {
        return new ReservationObjectAdapter$$Lambda$2(reservationObjectAdapter, helpThread);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToHelpThread(this.arg$2);
    }
}
