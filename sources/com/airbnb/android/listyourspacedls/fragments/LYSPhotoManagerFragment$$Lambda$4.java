package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.listing.adapters.PhotoManagerAdapter.Listener;

final /* synthetic */ class LYSPhotoManagerFragment$$Lambda$4 implements Listener {
    private final LYSPhotoManagerFragment arg$1;

    private LYSPhotoManagerFragment$$Lambda$4(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        this.arg$1 = lYSPhotoManagerFragment;
    }

    public static Listener lambdaFactory$(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        return new LYSPhotoManagerFragment$$Lambda$4(lYSPhotoManagerFragment);
    }

    public void photoDetailsRequested(long j) {
        this.arg$1.photoSelected(j);
    }
}
