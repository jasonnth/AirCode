package com.airbnb.android.lib.cancellation;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DLSCancelReservationSummaryFragment$$Lambda$2 implements Action1 {
    private final DLSCancelReservationSummaryFragment arg$1;

    private DLSCancelReservationSummaryFragment$$Lambda$2(DLSCancelReservationSummaryFragment dLSCancelReservationSummaryFragment) {
        this.arg$1 = dLSCancelReservationSummaryFragment;
    }

    public static Action1 lambdaFactory$(DLSCancelReservationSummaryFragment dLSCancelReservationSummaryFragment) {
        return new DLSCancelReservationSummaryFragment$$Lambda$2(dLSCancelReservationSummaryFragment);
    }

    public void call(Object obj) {
        DLSCancelReservationSummaryFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
