package com.airbnb.android.listyourspacedls;

import android.support.p000v4.app.FragmentManager.OnBackStackChangedListener;

final /* synthetic */ class LYSHomeActivity$$Lambda$4 implements OnBackStackChangedListener {
    private final LYSHomeActivity arg$1;

    private LYSHomeActivity$$Lambda$4(LYSHomeActivity lYSHomeActivity) {
        this.arg$1 = lYSHomeActivity;
    }

    public static OnBackStackChangedListener lambdaFactory$(LYSHomeActivity lYSHomeActivity) {
        return new LYSHomeActivity$$Lambda$4(lYSHomeActivity);
    }

    public void onBackStackChanged() {
        this.arg$1.updateProgressVisibility();
    }
}
