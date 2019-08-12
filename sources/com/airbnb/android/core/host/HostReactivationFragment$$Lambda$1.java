package com.airbnb.android.core.host;

import com.airbnb.android.core.responses.HostReactivationCopyResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostReactivationFragment$$Lambda$1 implements Action1 {
    private final HostReactivationFragment arg$1;

    private HostReactivationFragment$$Lambda$1(HostReactivationFragment hostReactivationFragment) {
        this.arg$1 = hostReactivationFragment;
    }

    public static Action1 lambdaFactory$(HostReactivationFragment hostReactivationFragment) {
        return new HostReactivationFragment$$Lambda$1(hostReactivationFragment);
    }

    public void call(Object obj) {
        this.arg$1.hostStandard = ((HostReactivationCopyResponse) obj).hostStandard;
    }
}
