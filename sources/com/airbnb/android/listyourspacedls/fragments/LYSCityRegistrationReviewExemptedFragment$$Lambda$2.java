package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationReviewExemptedFragment$$Lambda$2 implements Action1 {
    private final LYSCityRegistrationReviewExemptedFragment arg$1;

    private LYSCityRegistrationReviewExemptedFragment$$Lambda$2(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment) {
        this.arg$1 = lYSCityRegistrationReviewExemptedFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment) {
        return new LYSCityRegistrationReviewExemptedFragment$$Lambda$2(lYSCityRegistrationReviewExemptedFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.recyclerView, (AirRequestNetworkException) obj);
    }
}
