package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSGuestBookFragment$$Lambda$2 implements Action1 {
    private final LYSGuestBookFragment arg$1;

    private LYSGuestBookFragment$$Lambda$2(LYSGuestBookFragment lYSGuestBookFragment) {
        this.arg$1 = lYSGuestBookFragment;
    }

    public static Action1 lambdaFactory$(LYSGuestBookFragment lYSGuestBookFragment) {
        return new LYSGuestBookFragment$$Lambda$2(lYSGuestBookFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
