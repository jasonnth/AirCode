package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSAddressFragment$$Lambda$8 implements Action1 {
    private final LYSAddressFragment arg$1;

    private LYSAddressFragment$$Lambda$8(LYSAddressFragment lYSAddressFragment) {
        this.arg$1 = lYSAddressFragment;
    }

    public static Action1 lambdaFactory$(LYSAddressFragment lYSAddressFragment) {
        return new LYSAddressFragment$$Lambda$8(lYSAddressFragment);
    }

    public void call(Object obj) {
        LYSAddressFragment.lambda$new$10(this.arg$1, (AirBatchResponse) obj);
    }
}
