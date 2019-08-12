package com.airbnb.android.lib.cancellation.host;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CancellationOverviewFragment$$Lambda$1 implements Action1 {
    private final CancellationOverviewFragment arg$1;

    private CancellationOverviewFragment$$Lambda$1(CancellationOverviewFragment cancellationOverviewFragment) {
        this.arg$1 = cancellationOverviewFragment;
    }

    public static Action1 lambdaFactory$(CancellationOverviewFragment cancellationOverviewFragment) {
        return new CancellationOverviewFragment$$Lambda$1(cancellationOverviewFragment);
    }

    public void call(Object obj) {
        CancellationOverviewFragment.lambda$new$0(this.arg$1, (ReservationResponse) obj);
    }
}
