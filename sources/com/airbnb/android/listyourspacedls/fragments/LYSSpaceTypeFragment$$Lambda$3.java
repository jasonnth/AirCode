package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSSpaceTypeFragment$$Lambda$3 implements Action1 {
    private final LYSSpaceTypeFragment arg$1;

    private LYSSpaceTypeFragment$$Lambda$3(LYSSpaceTypeFragment lYSSpaceTypeFragment) {
        this.arg$1 = lYSSpaceTypeFragment;
    }

    public static Action1 lambdaFactory$(LYSSpaceTypeFragment lYSSpaceTypeFragment) {
        return new LYSSpaceTypeFragment$$Lambda$3(lYSSpaceTypeFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
