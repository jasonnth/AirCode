package com.airbnb.android.lib.cancellation;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DLSCancelReservationSummaryFragment$$Lambda$1 implements Action1 {
    private final DLSCancelReservationSummaryFragment arg$1;

    private DLSCancelReservationSummaryFragment$$Lambda$1(DLSCancelReservationSummaryFragment dLSCancelReservationSummaryFragment) {
        this.arg$1 = dLSCancelReservationSummaryFragment;
    }

    public static Action1 lambdaFactory$(DLSCancelReservationSummaryFragment dLSCancelReservationSummaryFragment) {
        return new DLSCancelReservationSummaryFragment$$Lambda$1(dLSCancelReservationSummaryFragment);
    }

    public void call(Object obj) {
        DLSCancelReservationSummaryFragment.lambda$new$0(this.arg$1, (ReservationResponse) obj);
    }
}
