package com.airbnb.android.lib.cancellation;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.CancellationReason;
import com.airbnb.android.lib.cancellation.CancelReservationReasonAdapter.Listener;

final /* synthetic */ class CancelReservationReasonAdapter$$Lambda$1 implements OnClickListener {
    private final Listener arg$1;
    private final CancellationReason arg$2;

    private CancelReservationReasonAdapter$$Lambda$1(Listener listener, CancellationReason cancellationReason) {
        this.arg$1 = listener;
        this.arg$2 = cancellationReason;
    }

    public static OnClickListener lambdaFactory$(Listener listener, CancellationReason cancellationReason) {
        return new CancelReservationReasonAdapter$$Lambda$1(listener, cancellationReason);
    }

    public void onClick(View view) {
        this.arg$1.cancelWithReason(this.arg$2);
    }
}
