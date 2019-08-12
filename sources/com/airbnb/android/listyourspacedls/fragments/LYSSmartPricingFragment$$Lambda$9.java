package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSSmartPricingFragment$$Lambda$9 implements Action1 {
    private final LYSSmartPricingFragment arg$1;

    private LYSSmartPricingFragment$$Lambda$9(LYSSmartPricingFragment lYSSmartPricingFragment) {
        this.arg$1 = lYSSmartPricingFragment;
    }

    public static Action1 lambdaFactory$(LYSSmartPricingFragment lYSSmartPricingFragment) {
        return new LYSSmartPricingFragment$$Lambda$9(lYSSmartPricingFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
