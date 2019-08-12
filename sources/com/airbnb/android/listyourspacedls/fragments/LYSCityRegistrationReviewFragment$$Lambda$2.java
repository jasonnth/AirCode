package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationReviewFragment$$Lambda$2 implements Action1 {
    private final LYSCityRegistrationReviewFragment arg$1;

    private LYSCityRegistrationReviewFragment$$Lambda$2(LYSCityRegistrationReviewFragment lYSCityRegistrationReviewFragment) {
        this.arg$1 = lYSCityRegistrationReviewFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationReviewFragment lYSCityRegistrationReviewFragment) {
        return new LYSCityRegistrationReviewFragment$$Lambda$2(lYSCityRegistrationReviewFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.recyclerView, (AirRequestNetworkException) obj);
    }
}
