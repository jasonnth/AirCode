package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Thread;

final /* synthetic */ class ReservationPickerAdapter$$Lambda$3 implements OnClickListener {
    private final ReservationPickerAdapter arg$1;
    private final Thread arg$2;

    private ReservationPickerAdapter$$Lambda$3(ReservationPickerAdapter reservationPickerAdapter, Thread thread) {
        this.arg$1 = reservationPickerAdapter;
        this.arg$2 = thread;
    }

    public static OnClickListener lambdaFactory$(ReservationPickerAdapter reservationPickerAdapter, Thread thread) {
        return new ReservationPickerAdapter$$Lambda$3(reservationPickerAdapter, thread);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToReservation(this.arg$2.getId());
    }
}
