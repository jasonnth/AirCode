package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import com.airbnb.android.lib.paidamenities.responses.FetchAllPaidAmenitiesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostAmenityListFragment$$Lambda$1 implements Action1 {
    private final HostAmenityListFragment arg$1;

    private HostAmenityListFragment$$Lambda$1(HostAmenityListFragment hostAmenityListFragment) {
        this.arg$1 = hostAmenityListFragment;
    }

    public static Action1 lambdaFactory$(HostAmenityListFragment hostAmenityListFragment) {
        return new HostAmenityListFragment$$Lambda$1(hostAmenityListFragment);
    }

    public void call(Object obj) {
        HostAmenityListFragment.lambda$new$1(this.arg$1, (FetchAllPaidAmenitiesResponse) obj);
    }
}
