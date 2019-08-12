package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSAddressFragment$$Lambda$10 implements Action1 {
    private final LYSAddressFragment arg$1;

    private LYSAddressFragment$$Lambda$10(LYSAddressFragment lYSAddressFragment) {
        this.arg$1 = lYSAddressFragment;
    }

    public static Action1 lambdaFactory$(LYSAddressFragment lYSAddressFragment) {
        return new LYSAddressFragment$$Lambda$10(lYSAddressFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.editAddressAdapter);
    }
}
