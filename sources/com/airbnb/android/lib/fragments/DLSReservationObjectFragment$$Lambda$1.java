package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DLSReservationObjectFragment$$Lambda$1 implements Action1 {
    private final DLSReservationObjectFragment arg$1;

    private DLSReservationObjectFragment$$Lambda$1(DLSReservationObjectFragment dLSReservationObjectFragment) {
        this.arg$1 = dLSReservationObjectFragment;
    }

    public static Action1 lambdaFactory$(DLSReservationObjectFragment dLSReservationObjectFragment) {
        return new DLSReservationObjectFragment$$Lambda$1(dLSReservationObjectFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleResponse(TripInformationProvider.create(((ReservationResponse) obj).reservation));
    }
}
