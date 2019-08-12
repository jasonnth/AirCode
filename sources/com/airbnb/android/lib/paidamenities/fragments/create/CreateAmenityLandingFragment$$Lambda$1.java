package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CreateAmenityLandingFragment$$Lambda$1 implements Action1 {
    private final CreateAmenityLandingFragment arg$1;

    private CreateAmenityLandingFragment$$Lambda$1(CreateAmenityLandingFragment createAmenityLandingFragment) {
        this.arg$1 = createAmenityLandingFragment;
    }

    public static Action1 lambdaFactory$(CreateAmenityLandingFragment createAmenityLandingFragment) {
        return new CreateAmenityLandingFragment$$Lambda$1(createAmenityLandingFragment);
    }

    public void call(Object obj) {
        CreateAmenityLandingFragment.lambda$new$0(this.arg$1, (ListingResponse) obj);
    }
}
