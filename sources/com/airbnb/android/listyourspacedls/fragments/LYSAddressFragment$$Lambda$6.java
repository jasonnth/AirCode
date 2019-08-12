package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSAddressFragment$$Lambda$6 implements Action1 {
    private final LYSAddressFragment arg$1;

    private LYSAddressFragment$$Lambda$6(LYSAddressFragment lYSAddressFragment) {
        this.arg$1 = lYSAddressFragment;
    }

    public static Action1 lambdaFactory$(LYSAddressFragment lYSAddressFragment) {
        return new LYSAddressFragment$$Lambda$6(lYSAddressFragment);
    }

    public void call(Object obj) {
        this.arg$1.executeFetchDataBatchRequest();
    }
}
