package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationPickerFragment$$Lambda$5 implements Action1 {
    private final ReservationPickerFragment arg$1;

    private ReservationPickerFragment$$Lambda$5(ReservationPickerFragment reservationPickerFragment) {
        this.arg$1 = reservationPickerFragment;
    }

    public static Action1 lambdaFactory$(ReservationPickerFragment reservationPickerFragment) {
        return new ReservationPickerFragment$$Lambda$5(reservationPickerFragment);
    }

    public void call(Object obj) {
        ReservationPickerFragment.lambda$new$4(this.arg$1, (AirBatchResponse) obj);
    }
}
