package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSLocalLawsFragment$$Lambda$1 implements Action1 {
    private final LYSLocalLawsFragment arg$1;

    private LYSLocalLawsFragment$$Lambda$1(LYSLocalLawsFragment lYSLocalLawsFragment) {
        this.arg$1 = lYSLocalLawsFragment;
    }

    public static Action1 lambdaFactory$(LYSLocalLawsFragment lYSLocalLawsFragment) {
        return new LYSLocalLawsFragment$$Lambda$1(lYSLocalLawsFragment);
    }

    public void call(Object obj) {
        LYSLocalLawsFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
