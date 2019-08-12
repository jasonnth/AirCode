package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Reservation;

final /* synthetic */ class ReservationPickerAdapter$$Lambda$2 implements OnClickListener {
    private final ReservationPickerAdapter arg$1;
    private final Reservation arg$2;

    private ReservationPickerAdapter$$Lambda$2(ReservationPickerAdapter reservationPickerAdapter, Reservation reservation) {
        this.arg$1 = reservationPickerAdapter;
        this.arg$2 = reservation;
    }

    public static OnClickListener lambdaFactory$(ReservationPickerAdapter reservationPickerAdapter, Reservation reservation) {
        return new ReservationPickerAdapter$$Lambda$2(reservationPickerAdapter, reservation);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToReservation(this.arg$2.getConfirmationCode());
    }
}
