package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CreateAmenityLandingFragment$$Lambda$2 implements Action1 {
    private final CreateAmenityLandingFragment arg$1;

    private CreateAmenityLandingFragment$$Lambda$2(CreateAmenityLandingFragment createAmenityLandingFragment) {
        this.arg$1 = createAmenityLandingFragment;
    }

    public static Action1 lambdaFactory$(CreateAmenityLandingFragment createAmenityLandingFragment) {
        return new CreateAmenityLandingFragment$$Lambda$2(createAmenityLandingFragment);
    }

    public void call(Object obj) {
        CreateAmenityLandingFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
