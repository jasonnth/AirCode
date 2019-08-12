package com.airbnb.android.fixit.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class FixItItemFragment$$Lambda$2 implements Action1 {
    private final FixItItemFragment arg$1;

    private FixItItemFragment$$Lambda$2(FixItItemFragment fixItItemFragment) {
        this.arg$1 = fixItItemFragment;
    }

    public static Action1 lambdaFactory$(FixItItemFragment fixItItemFragment) {
        return new FixItItemFragment$$Lambda$2(fixItItemFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
