package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSBasePriceFragment$$Lambda$7 implements Action1 {
    private final LYSBasePriceFragment arg$1;

    private LYSBasePriceFragment$$Lambda$7(LYSBasePriceFragment lYSBasePriceFragment) {
        this.arg$1 = lYSBasePriceFragment;
    }

    public static Action1 lambdaFactory$(LYSBasePriceFragment lYSBasePriceFragment) {
        return new LYSBasePriceFragment$$Lambda$7(lYSBasePriceFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
