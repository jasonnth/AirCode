package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSLocalLawsFragment$$Lambda$3 implements Action1 {
    private final LYSLocalLawsFragment arg$1;

    private LYSLocalLawsFragment$$Lambda$3(LYSLocalLawsFragment lYSLocalLawsFragment) {
        this.arg$1 = lYSLocalLawsFragment;
    }

    public static Action1 lambdaFactory$(LYSLocalLawsFragment lYSLocalLawsFragment) {
        return new LYSLocalLawsFragment$$Lambda$3(lYSLocalLawsFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), null);
    }
}
