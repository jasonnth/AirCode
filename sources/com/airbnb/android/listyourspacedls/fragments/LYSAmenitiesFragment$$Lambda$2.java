package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSAmenitiesFragment$$Lambda$2 implements Action1 {
    private final LYSAmenitiesFragment arg$1;

    private LYSAmenitiesFragment$$Lambda$2(LYSAmenitiesFragment lYSAmenitiesFragment) {
        this.arg$1 = lYSAmenitiesFragment;
    }

    public static Action1 lambdaFactory$(LYSAmenitiesFragment lYSAmenitiesFragment) {
        return new LYSAmenitiesFragment$$Lambda$2(lYSAmenitiesFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
