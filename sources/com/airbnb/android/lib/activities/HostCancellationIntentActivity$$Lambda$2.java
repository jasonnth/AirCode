package com.airbnb.android.lib.activities;

import android.content.Context;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class HostCancellationIntentActivity$$Lambda$2 implements Action1 {
    private final HostCancellationIntentActivity arg$1;

    private HostCancellationIntentActivity$$Lambda$2(HostCancellationIntentActivity hostCancellationIntentActivity) {
        this.arg$1 = hostCancellationIntentActivity;
    }

    public static Action1 lambdaFactory$(HostCancellationIntentActivity hostCancellationIntentActivity) {
        return new HostCancellationIntentActivity$$Lambda$2(hostCancellationIntentActivity);
    }

    public void call(Object obj) {
        NetworkUtil.toastNetworkError((Context) this.arg$1, (NetworkException) (AirRequestNetworkException) obj);
    }
}
