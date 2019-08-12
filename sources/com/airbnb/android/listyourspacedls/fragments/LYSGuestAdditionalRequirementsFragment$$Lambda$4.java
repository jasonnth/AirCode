package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSGuestAdditionalRequirementsFragment$$Lambda$4 implements Action1 {
    private final LYSGuestAdditionalRequirementsFragment arg$1;

    private LYSGuestAdditionalRequirementsFragment$$Lambda$4(LYSGuestAdditionalRequirementsFragment lYSGuestAdditionalRequirementsFragment) {
        this.arg$1 = lYSGuestAdditionalRequirementsFragment;
    }

    public static Action1 lambdaFactory$(LYSGuestAdditionalRequirementsFragment lYSGuestAdditionalRequirementsFragment) {
        return new LYSGuestAdditionalRequirementsFragment$$Lambda$4(lYSGuestAdditionalRequirementsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
