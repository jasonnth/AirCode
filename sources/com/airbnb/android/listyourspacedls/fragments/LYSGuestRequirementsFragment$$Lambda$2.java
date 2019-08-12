package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSGuestRequirementsFragment$$Lambda$2 implements Action1 {
    private final LYSGuestRequirementsFragment arg$1;

    private LYSGuestRequirementsFragment$$Lambda$2(LYSGuestRequirementsFragment lYSGuestRequirementsFragment) {
        this.arg$1 = lYSGuestRequirementsFragment;
    }

    public static Action1 lambdaFactory$(LYSGuestRequirementsFragment lYSGuestRequirementsFragment) {
        return new LYSGuestRequirementsFragment$$Lambda$2(lYSGuestRequirementsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
