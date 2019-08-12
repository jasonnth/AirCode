package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class LYSBaseFragment$$Lambda$1 implements OnBackListener {
    private final LYSBaseFragment arg$1;

    private LYSBaseFragment$$Lambda$1(LYSBaseFragment lYSBaseFragment) {
        this.arg$1 = lYSBaseFragment;
    }

    public static OnBackListener lambdaFactory$(LYSBaseFragment lYSBaseFragment) {
        return new LYSBaseFragment$$Lambda$1(lYSBaseFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.onBackPressed();
    }
}
