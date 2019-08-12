package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationExemptionFragment$$Lambda$2 implements Action1 {
    private final LYSCityRegistrationExemptionFragment arg$1;

    private LYSCityRegistrationExemptionFragment$$Lambda$2(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment) {
        this.arg$1 = lYSCityRegistrationExemptionFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment) {
        return new LYSCityRegistrationExemptionFragment$$Lambda$2(lYSCityRegistrationExemptionFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.recyclerView, (AirRequestNetworkException) obj);
    }
}
