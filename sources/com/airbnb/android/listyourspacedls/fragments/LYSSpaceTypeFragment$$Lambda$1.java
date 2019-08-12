package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSSpaceTypeFragment$$Lambda$1 implements Action1 {
    private final LYSSpaceTypeFragment arg$1;

    private LYSSpaceTypeFragment$$Lambda$1(LYSSpaceTypeFragment lYSSpaceTypeFragment) {
        this.arg$1 = lYSSpaceTypeFragment;
    }

    public static Action1 lambdaFactory$(LYSSpaceTypeFragment lYSSpaceTypeFragment) {
        return new LYSSpaceTypeFragment$$Lambda$1(lYSSpaceTypeFragment);
    }

    public void call(Object obj) {
        LYSSpaceTypeFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
