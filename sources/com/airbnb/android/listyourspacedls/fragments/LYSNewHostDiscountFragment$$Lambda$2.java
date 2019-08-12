package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSNewHostDiscountFragment$$Lambda$2 implements Action1 {
    private final LYSNewHostDiscountFragment arg$1;

    private LYSNewHostDiscountFragment$$Lambda$2(LYSNewHostDiscountFragment lYSNewHostDiscountFragment) {
        this.arg$1 = lYSNewHostDiscountFragment;
    }

    public static Action1 lambdaFactory$(LYSNewHostDiscountFragment lYSNewHostDiscountFragment) {
        return new LYSNewHostDiscountFragment$$Lambda$2(lYSNewHostDiscountFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
