package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.p027n2.utils.ValueAnimatorFactory.Listener;

final /* synthetic */ class LYSLandingFragment$$Lambda$1 implements Listener {
    private final LYSLandingFragment arg$1;

    private LYSLandingFragment$$Lambda$1(LYSLandingFragment lYSLandingFragment) {
        this.arg$1 = lYSLandingFragment;
    }

    public static Listener lambdaFactory$(LYSLandingFragment lYSLandingFragment) {
        return new LYSLandingFragment$$Lambda$1(lYSLandingFragment);
    }

    public void valueUpdated(Float f) {
        this.arg$1.scrollView.setScrollY((int) (((float) this.arg$1.scrollView.getHeight()) * f.floatValue()));
    }
}
