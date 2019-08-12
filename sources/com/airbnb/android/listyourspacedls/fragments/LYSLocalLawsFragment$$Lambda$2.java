package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSLocalLawsFragment$$Lambda$2 implements Action1 {
    private final LYSLocalLawsFragment arg$1;

    private LYSLocalLawsFragment$$Lambda$2(LYSLocalLawsFragment lYSLocalLawsFragment) {
        this.arg$1 = lYSLocalLawsFragment;
    }

    public static Action1 lambdaFactory$(LYSLocalLawsFragment lYSLocalLawsFragment) {
        return new LYSLocalLawsFragment$$Lambda$2(lYSLocalLawsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
