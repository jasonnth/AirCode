package com.airbnb.android.lib.cancellation;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DLSCancelReservationReasonFragment$$Lambda$2 implements Action1 {
    private final DLSCancelReservationReasonFragment arg$1;

    private DLSCancelReservationReasonFragment$$Lambda$2(DLSCancelReservationReasonFragment dLSCancelReservationReasonFragment) {
        this.arg$1 = dLSCancelReservationReasonFragment;
    }

    public static Action1 lambdaFactory$(DLSCancelReservationReasonFragment dLSCancelReservationReasonFragment) {
        return new DLSCancelReservationReasonFragment$$Lambda$2(dLSCancelReservationReasonFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleResponse(((ReservationResponse) obj).reservation);
    }
}
