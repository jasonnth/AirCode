package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.android.lib.paidamenities.responses.PaidAmenityResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CreatedAmenityFragment$$Lambda$1 implements Action1 {
    private final CreatedAmenityFragment arg$1;

    private CreatedAmenityFragment$$Lambda$1(CreatedAmenityFragment createdAmenityFragment) {
        this.arg$1 = createdAmenityFragment;
    }

    public static Action1 lambdaFactory$(CreatedAmenityFragment createdAmenityFragment) {
        return new CreatedAmenityFragment$$Lambda$1(createdAmenityFragment);
    }

    public void call(Object obj) {
        CreatedAmenityFragment.lambda$new$1(this.arg$1, (PaidAmenityResponse) obj);
    }
}
