package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSExactLocationFragment$$Lambda$2 implements Action1 {
    private final LYSExactLocationFragment arg$1;

    private LYSExactLocationFragment$$Lambda$2(LYSExactLocationFragment lYSExactLocationFragment) {
        this.arg$1 = lYSExactLocationFragment;
    }

    public static Action1 lambdaFactory$(LYSExactLocationFragment lYSExactLocationFragment) {
        return new LYSExactLocationFragment$$Lambda$2(lYSExactLocationFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
