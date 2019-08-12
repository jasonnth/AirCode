package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSAddressFragment$$Lambda$9 implements Action1 {
    private final LYSAddressFragment arg$1;

    private LYSAddressFragment$$Lambda$9(LYSAddressFragment lYSAddressFragment) {
        this.arg$1 = lYSAddressFragment;
    }

    public static Action1 lambdaFactory$(LYSAddressFragment lYSAddressFragment) {
        return new LYSAddressFragment$$Lambda$9(lYSAddressFragment);
    }

    public void call(Object obj) {
        this.arg$1.errorSnackbar = NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, LYSAddressFragment$$Lambda$12.lambdaFactory$(this.arg$1));
    }
}
