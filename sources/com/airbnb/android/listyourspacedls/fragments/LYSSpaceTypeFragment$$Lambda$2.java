package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSSpaceTypeFragment$$Lambda$2 implements Action1 {
    private final LYSSpaceTypeFragment arg$1;

    private LYSSpaceTypeFragment$$Lambda$2(LYSSpaceTypeFragment lYSSpaceTypeFragment) {
        this.arg$1 = lYSSpaceTypeFragment;
    }

    public static Action1 lambdaFactory$(LYSSpaceTypeFragment lYSSpaceTypeFragment) {
        return new LYSSpaceTypeFragment$$Lambda$2(lYSSpaceTypeFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
