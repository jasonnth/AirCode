package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationApplicationFragment$$Lambda$2 implements Action1 {
    private final LYSCityRegistrationApplicationFragment arg$1;

    private LYSCityRegistrationApplicationFragment$$Lambda$2(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment) {
        this.arg$1 = lYSCityRegistrationApplicationFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment) {
        return new LYSCityRegistrationApplicationFragment$$Lambda$2(lYSCityRegistrationApplicationFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.recyclerView, (AirRequestNetworkException) obj);
    }
}
