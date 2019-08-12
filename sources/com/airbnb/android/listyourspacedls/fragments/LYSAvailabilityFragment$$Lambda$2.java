package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSAvailabilityFragment$$Lambda$2 implements Action1 {
    private final LYSAvailabilityFragment arg$1;

    private LYSAvailabilityFragment$$Lambda$2(LYSAvailabilityFragment lYSAvailabilityFragment) {
        this.arg$1 = lYSAvailabilityFragment;
    }

    public static Action1 lambdaFactory$(LYSAvailabilityFragment lYSAvailabilityFragment) {
        return new LYSAvailabilityFragment$$Lambda$2(lYSAvailabilityFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
