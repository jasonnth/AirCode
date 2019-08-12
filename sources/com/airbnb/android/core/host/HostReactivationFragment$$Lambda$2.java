package com.airbnb.android.core.host;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class HostReactivationFragment$$Lambda$2 implements Action1 {
    private final HostReactivationFragment arg$1;

    private HostReactivationFragment$$Lambda$2(HostReactivationFragment hostReactivationFragment) {
        this.arg$1 = hostReactivationFragment;
    }

    public static Action1 lambdaFactory$(HostReactivationFragment hostReactivationFragment) {
        return new HostReactivationFragment$$Lambda$2(hostReactivationFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, HostReactivationFragment$$Lambda$6.lambdaFactory$(this.arg$1));
    }
}
