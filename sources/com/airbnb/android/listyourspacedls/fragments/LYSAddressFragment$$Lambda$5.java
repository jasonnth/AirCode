package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSAddressFragment$$Lambda$5 implements Action1 {
    private final LYSAddressFragment arg$1;

    private LYSAddressFragment$$Lambda$5(LYSAddressFragment lYSAddressFragment) {
        this.arg$1 = lYSAddressFragment;
    }

    public static Action1 lambdaFactory$(LYSAddressFragment lYSAddressFragment) {
        return new LYSAddressFragment$$Lambda$5(lYSAddressFragment);
    }

    public void call(Object obj) {
        LYSAddressFragment.lambda$new$6(this.arg$1, (AirRequestNetworkException) obj);
    }
}
