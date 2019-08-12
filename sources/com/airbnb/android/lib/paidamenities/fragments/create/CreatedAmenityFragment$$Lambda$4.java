package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CreatedAmenityFragment$$Lambda$4 implements Action1 {
    private final CreatedAmenityFragment arg$1;

    private CreatedAmenityFragment$$Lambda$4(CreatedAmenityFragment createdAmenityFragment) {
        this.arg$1 = createdAmenityFragment;
    }

    public static Action1 lambdaFactory$(CreatedAmenityFragment createdAmenityFragment) {
        return new CreatedAmenityFragment$$Lambda$4(createdAmenityFragment);
    }

    public void call(Object obj) {
        CreatedAmenityFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
