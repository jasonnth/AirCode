package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSAvailabilityFragment$$Lambda$3 implements Action1 {
    private final LYSAvailabilityFragment arg$1;

    private LYSAvailabilityFragment$$Lambda$3(LYSAvailabilityFragment lYSAvailabilityFragment) {
        this.arg$1 = lYSAvailabilityFragment;
    }

    public static Action1 lambdaFactory$(LYSAvailabilityFragment lYSAvailabilityFragment) {
        return new LYSAvailabilityFragment$$Lambda$3(lYSAvailabilityFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.settingsAdapter);
    }
}
