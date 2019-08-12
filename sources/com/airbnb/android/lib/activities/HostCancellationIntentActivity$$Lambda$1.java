package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostCancellationIntentActivity$$Lambda$1 implements Action1 {
    private final HostCancellationIntentActivity arg$1;

    private HostCancellationIntentActivity$$Lambda$1(HostCancellationIntentActivity hostCancellationIntentActivity) {
        this.arg$1 = hostCancellationIntentActivity;
    }

    public static Action1 lambdaFactory$(HostCancellationIntentActivity hostCancellationIntentActivity) {
        return new HostCancellationIntentActivity$$Lambda$1(hostCancellationIntentActivity);
    }

    public void call(Object obj) {
        HostCancellationIntentActivity.lambda$new$0(this.arg$1, (ReservationResponse) obj);
    }
}
