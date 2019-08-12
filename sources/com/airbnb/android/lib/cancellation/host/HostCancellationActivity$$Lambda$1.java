package com.airbnb.android.lib.cancellation.host;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostCancellationActivity$$Lambda$1 implements Action1 {
    private final HostCancellationActivity arg$1;

    private HostCancellationActivity$$Lambda$1(HostCancellationActivity hostCancellationActivity) {
        this.arg$1 = hostCancellationActivity;
    }

    public static Action1 lambdaFactory$(HostCancellationActivity hostCancellationActivity) {
        return new HostCancellationActivity$$Lambda$1(hostCancellationActivity);
    }

    public void call(Object obj) {
        HostCancellationActivity.lambda$new$0(this.arg$1, (ReservationResponse) obj);
    }
}
