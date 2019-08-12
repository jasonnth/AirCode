package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSDiscountsFragment$$Lambda$2 implements Action1 {
    private final LYSDiscountsFragment arg$1;

    private LYSDiscountsFragment$$Lambda$2(LYSDiscountsFragment lYSDiscountsFragment) {
        this.arg$1 = lYSDiscountsFragment;
    }

    public static Action1 lambdaFactory$(LYSDiscountsFragment lYSDiscountsFragment) {
        return new LYSDiscountsFragment$$Lambda$2(lYSDiscountsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
