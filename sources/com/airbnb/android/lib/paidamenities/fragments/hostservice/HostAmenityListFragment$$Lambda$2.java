package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class HostAmenityListFragment$$Lambda$2 implements Action1 {
    private final HostAmenityListFragment arg$1;

    private HostAmenityListFragment$$Lambda$2(HostAmenityListFragment hostAmenityListFragment) {
        this.arg$1 = hostAmenityListFragment;
    }

    public static Action1 lambdaFactory$(HostAmenityListFragment hostAmenityListFragment) {
        return new HostAmenityListFragment$$Lambda$2(hostAmenityListFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
