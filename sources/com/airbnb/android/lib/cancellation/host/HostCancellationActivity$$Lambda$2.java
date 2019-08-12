package com.airbnb.android.lib.cancellation.host;

import android.content.Context;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class HostCancellationActivity$$Lambda$2 implements Action1 {
    private final HostCancellationActivity arg$1;

    private HostCancellationActivity$$Lambda$2(HostCancellationActivity hostCancellationActivity) {
        this.arg$1 = hostCancellationActivity;
    }

    public static Action1 lambdaFactory$(HostCancellationActivity hostCancellationActivity) {
        return new HostCancellationActivity$$Lambda$2(hostCancellationActivity);
    }

    public void call(Object obj) {
        NetworkUtil.toastNetworkError((Context) this.arg$1, (NetworkException) (AirRequestNetworkException) obj);
    }
}
