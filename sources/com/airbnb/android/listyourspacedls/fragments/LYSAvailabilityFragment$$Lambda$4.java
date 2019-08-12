package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSAvailabilityFragment$$Lambda$4 implements OnClickListener {
    private final LYSAvailabilityFragment arg$1;

    private LYSAvailabilityFragment$$Lambda$4(LYSAvailabilityFragment lYSAvailabilityFragment) {
        this.arg$1 = lYSAvailabilityFragment;
    }

    public static OnClickListener lambdaFactory$(LYSAvailabilityFragment lYSAvailabilityFragment) {
        return new LYSAvailabilityFragment$$Lambda$4(lYSAvailabilityFragment);
    }

    public void onClick(View view) {
        this.arg$1.showTipModal();
    }
}
