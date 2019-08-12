package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.C5990Guidebook;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$12 implements OnClickListener {
    private final ReservationObjectAdapter arg$1;
    private final C5990Guidebook arg$2;

    private ReservationObjectAdapter$$Lambda$12(ReservationObjectAdapter reservationObjectAdapter, C5990Guidebook guidebook) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = guidebook;
    }

    public static OnClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, C5990Guidebook guidebook) {
        return new ReservationObjectAdapter$$Lambda$12(reservationObjectAdapter, guidebook);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToGuidebook(this.arg$2);
    }
}
