package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSGuestAdditionalRequirementsFragment$$Lambda$5 implements Action1 {
    private final LYSGuestAdditionalRequirementsFragment arg$1;

    private LYSGuestAdditionalRequirementsFragment$$Lambda$5(LYSGuestAdditionalRequirementsFragment lYSGuestAdditionalRequirementsFragment) {
        this.arg$1 = lYSGuestAdditionalRequirementsFragment;
    }

    public static Action1 lambdaFactory$(LYSGuestAdditionalRequirementsFragment lYSGuestAdditionalRequirementsFragment) {
        return new LYSGuestAdditionalRequirementsFragment$$Lambda$5(lYSGuestAdditionalRequirementsFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.additionalRequirementsAdapter);
    }
}
