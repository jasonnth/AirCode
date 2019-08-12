package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.p027n2.primitives.AirButton;

final /* synthetic */ class LYSPhotoManagerFragment$$Lambda$6 implements Runnable {
    private final AirButton arg$1;

    private LYSPhotoManagerFragment$$Lambda$6(AirButton airButton) {
        this.arg$1 = airButton;
    }

    public static Runnable lambdaFactory$(AirButton airButton) {
        return new LYSPhotoManagerFragment$$Lambda$6(airButton);
    }

    public void run() {
        this.arg$1.setVisibility(0);
    }
}
