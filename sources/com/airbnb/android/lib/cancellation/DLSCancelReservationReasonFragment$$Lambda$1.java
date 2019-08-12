package com.airbnb.android.lib.cancellation;

import com.airbnb.android.core.enums.CancellationReason;
import com.airbnb.android.lib.cancellation.CancelReservationReasonAdapter.Listener;

final /* synthetic */ class DLSCancelReservationReasonFragment$$Lambda$1 implements Listener {
    private final DLSCancelReservationReasonFragment arg$1;

    private DLSCancelReservationReasonFragment$$Lambda$1(DLSCancelReservationReasonFragment dLSCancelReservationReasonFragment) {
        this.arg$1 = dLSCancelReservationReasonFragment;
    }

    public static Listener lambdaFactory$(DLSCancelReservationReasonFragment dLSCancelReservationReasonFragment) {
        return new DLSCancelReservationReasonFragment$$Lambda$1(dLSCancelReservationReasonFragment);
    }

    public void cancelWithReason(CancellationReason cancellationReason) {
        this.arg$1.cancelActivity.onStepFinished(CancelReservationStep.Reason, this.arg$1.cancellationData.toBuilder().cancellationReason(cancellationReason).build());
    }
}
