package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCurrencyFragment$$Lambda$2 implements Action1 {
    private final LYSCurrencyFragment arg$1;

    private LYSCurrencyFragment$$Lambda$2(LYSCurrencyFragment lYSCurrencyFragment) {
        this.arg$1 = lYSCurrencyFragment;
    }

    public static Action1 lambdaFactory$(LYSCurrencyFragment lYSCurrencyFragment) {
        return new LYSCurrencyFragment$$Lambda$2(lYSCurrencyFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, LYSCurrencyFragment$$Lambda$3.lambdaFactory$(this.arg$1));
    }
}
