package com.airbnb.android.lib.host.stats;

import p032rx.functions.Action1;

final /* synthetic */ class HostDemandsDetailFragment$$Lambda$2 implements Action1 {
    private final HostDemandsDetailFragment arg$1;

    private HostDemandsDetailFragment$$Lambda$2(HostDemandsDetailFragment hostDemandsDetailFragment) {
        this.arg$1 = hostDemandsDetailFragment;
    }

    public static Action1 lambdaFactory$(HostDemandsDetailFragment hostDemandsDetailFragment) {
        return new HostDemandsDetailFragment$$Lambda$2(hostDemandsDetailFragment);
    }

    public void call(Object obj) {
        HostDemandsDetailFragment.lambda$new$4(this.arg$1, (ListingDemandDetailsResponse) obj);
    }
}
