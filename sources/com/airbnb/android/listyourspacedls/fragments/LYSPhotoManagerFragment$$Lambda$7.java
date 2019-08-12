package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.p027n2.primitives.AirButton;

final /* synthetic */ class LYSPhotoManagerFragment$$Lambda$7 implements Runnable {
    private final AirButton arg$1;
    private final boolean arg$2;

    private LYSPhotoManagerFragment$$Lambda$7(AirButton airButton, boolean z) {
        this.arg$1 = airButton;
        this.arg$2 = z;
    }

    public static Runnable lambdaFactory$(AirButton airButton, boolean z) {
        return new LYSPhotoManagerFragment$$Lambda$7(airButton, z);
    }

    public void run() {
        LYSPhotoManagerFragment.lambda$animateButtonVisibility$4(this.arg$1, this.arg$2);
    }
}
