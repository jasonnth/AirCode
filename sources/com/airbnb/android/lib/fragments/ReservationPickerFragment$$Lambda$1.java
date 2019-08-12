package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.FetchAllReservationsResponse;
import java.util.ArrayList;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationPickerFragment$$Lambda$1 implements Action1 {
    private final ReservationPickerFragment arg$1;

    private ReservationPickerFragment$$Lambda$1(ReservationPickerFragment reservationPickerFragment) {
        this.arg$1 = reservationPickerFragment;
    }

    public static Action1 lambdaFactory$(ReservationPickerFragment reservationPickerFragment) {
        return new ReservationPickerFragment$$Lambda$1(reservationPickerFragment);
    }

    public void call(Object obj) {
        this.arg$1.reservations = new ArrayList<>(((FetchAllReservationsResponse) obj).reservations);
    }
}
