package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class LYSPublishFragment$$Lambda$5 implements OnBackListener {
    private final LYSPublishFragment arg$1;

    private LYSPublishFragment$$Lambda$5(LYSPublishFragment lYSPublishFragment) {
        this.arg$1 = lYSPublishFragment;
    }

    public static OnBackListener lambdaFactory$(LYSPublishFragment lYSPublishFragment) {
        return new LYSPublishFragment$$Lambda$5(lYSPublishFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.jitneyLogger.logPublishBackButton(Long.valueOf(this.arg$1.controller.getListing().getId()));
    }
}
