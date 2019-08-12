package com.airbnb.android.listing.views;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

final /* synthetic */ class TipView$$Lambda$3 implements OnGlobalLayoutListener {
    private final TipView arg$1;

    private TipView$$Lambda$3(TipView tipView) {
        this.arg$1 = tipView;
    }

    public static OnGlobalLayoutListener lambdaFactory$(TipView tipView) {
        return new TipView$$Lambda$3(tipView);
    }

    public void onGlobalLayout() {
        this.arg$1.updateViewVisibility();
    }
}
