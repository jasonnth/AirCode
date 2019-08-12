package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSAmenitiesFragment$$Lambda$3 implements Action1 {
    private final LYSAmenitiesFragment arg$1;

    private LYSAmenitiesFragment$$Lambda$3(LYSAmenitiesFragment lYSAmenitiesFragment) {
        this.arg$1 = lYSAmenitiesFragment;
    }

    public static Action1 lambdaFactory$(LYSAmenitiesFragment lYSAmenitiesFragment) {
        return new LYSAmenitiesFragment$$Lambda$3(lYSAmenitiesFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.amenitiesAdapter);
    }
}
