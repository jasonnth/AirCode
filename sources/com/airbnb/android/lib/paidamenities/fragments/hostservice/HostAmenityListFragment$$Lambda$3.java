package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import com.airbnb.android.lib.paidamenities.responses.PaidAmenityResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostAmenityListFragment$$Lambda$3 implements Action1 {
    private final HostAmenityListFragment arg$1;

    private HostAmenityListFragment$$Lambda$3(HostAmenityListFragment hostAmenityListFragment) {
        this.arg$1 = hostAmenityListFragment;
    }

    public static Action1 lambdaFactory$(HostAmenityListFragment hostAmenityListFragment) {
        return new HostAmenityListFragment$$Lambda$3(hostAmenityListFragment);
    }

    public void call(Object obj) {
        this.arg$1.deletePaidAmenity(((PaidAmenityResponse) obj).paidAmenity);
    }
}
