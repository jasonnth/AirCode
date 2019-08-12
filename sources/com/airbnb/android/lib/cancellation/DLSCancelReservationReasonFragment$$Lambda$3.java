package com.airbnb.android.lib.cancellation;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DLSCancelReservationReasonFragment$$Lambda$3 implements Action1 {
    private final DLSCancelReservationReasonFragment arg$1;

    private DLSCancelReservationReasonFragment$$Lambda$3(DLSCancelReservationReasonFragment dLSCancelReservationReasonFragment) {
        this.arg$1 = dLSCancelReservationReasonFragment;
    }

    public static Action1 lambdaFactory$(DLSCancelReservationReasonFragment dLSCancelReservationReasonFragment) {
        return new DLSCancelReservationReasonFragment$$Lambda$3(dLSCancelReservationReasonFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleErrorResponse((AirRequestNetworkException) obj);
    }
}
