package com.airbnb.android.listyourspacedls.fragments;

final /* synthetic */ class LYSPhotoManagerFragment$$Lambda$5 implements Runnable {
    private final LYSPhotoManagerFragment arg$1;

    private LYSPhotoManagerFragment$$Lambda$5(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        this.arg$1 = lYSPhotoManagerFragment;
    }

    public static Runnable lambdaFactory$(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        return new LYSPhotoManagerFragment$$Lambda$5(lYSPhotoManagerFragment);
    }

    public void run() {
        this.arg$1.updateMenuViewState();
    }
}
