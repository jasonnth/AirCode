package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.models.Reservation;
import com.google.common.base.Function;

final /* synthetic */ class ReservationPickerAdapter$$Lambda$1 implements Function {
    private final ReservationPickerAdapter arg$1;

    private ReservationPickerAdapter$$Lambda$1(ReservationPickerAdapter reservationPickerAdapter) {
        this.arg$1 = reservationPickerAdapter;
    }

    public static Function lambdaFactory$(ReservationPickerAdapter reservationPickerAdapter) {
        return new ReservationPickerAdapter$$Lambda$1(reservationPickerAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.reservationToModel((Reservation) obj);
    }
}
