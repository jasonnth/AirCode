package com.airbnb.android.core.host;

import com.airbnb.android.core.responses.UserResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostReactivationFragment$$Lambda$4 implements Action1 {
    private final HostReactivationFragment arg$1;

    private HostReactivationFragment$$Lambda$4(HostReactivationFragment hostReactivationFragment) {
        this.arg$1 = hostReactivationFragment;
    }

    public static Action1 lambdaFactory$(HostReactivationFragment hostReactivationFragment) {
        return new HostReactivationFragment$$Lambda$4(hostReactivationFragment);
    }

    public void call(Object obj) {
        HostReactivationFragment.lambda$new$4(this.arg$1, (UserResponse) obj);
    }
}
