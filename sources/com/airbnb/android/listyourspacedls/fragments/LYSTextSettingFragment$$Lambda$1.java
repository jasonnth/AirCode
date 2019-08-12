package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSTextSettingFragment$$Lambda$1 implements Action1 {
    private final LYSTextSettingFragment arg$1;

    private LYSTextSettingFragment$$Lambda$1(LYSTextSettingFragment lYSTextSettingFragment) {
        this.arg$1 = lYSTextSettingFragment;
    }

    public static Action1 lambdaFactory$(LYSTextSettingFragment lYSTextSettingFragment) {
        return new LYSTextSettingFragment$$Lambda$1(lYSTextSettingFragment);
    }

    public void call(Object obj) {
        LYSTextSettingFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
