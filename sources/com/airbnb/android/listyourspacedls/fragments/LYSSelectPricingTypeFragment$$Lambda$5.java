package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSSelectPricingTypeFragment$$Lambda$5 implements Action1 {
    private final LYSSelectPricingTypeFragment arg$1;

    private LYSSelectPricingTypeFragment$$Lambda$5(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment) {
        this.arg$1 = lYSSelectPricingTypeFragment;
    }

    public static Action1 lambdaFactory$(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment) {
        return new LYSSelectPricingTypeFragment$$Lambda$5(lYSSelectPricingTypeFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
