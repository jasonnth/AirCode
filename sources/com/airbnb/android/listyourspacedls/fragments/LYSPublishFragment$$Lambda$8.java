package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSPublishFragment$$Lambda$8 implements OnClickListener {
    private final LYSPublishFragment arg$1;

    private LYSPublishFragment$$Lambda$8(LYSPublishFragment lYSPublishFragment) {
        this.arg$1 = lYSPublishFragment;
    }

    public static OnClickListener lambdaFactory$(LYSPublishFragment lYSPublishFragment) {
        return new LYSPublishFragment$$Lambda$8(lYSPublishFragment);
    }

    public void onClick(View view) {
        this.arg$1.showTipModal();
    }
}
