package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.photouploadmanager.PhotoUploadListenerUtil.CatchAllListener;

final /* synthetic */ class LYSPhotoManagerFragment$$Lambda$1 implements CatchAllListener {
    private final LYSPhotoManagerFragment arg$1;

    private LYSPhotoManagerFragment$$Lambda$1(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        this.arg$1 = lYSPhotoManagerFragment;
    }

    public static CatchAllListener lambdaFactory$(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        return new LYSPhotoManagerFragment$$Lambda$1(lYSPhotoManagerFragment);
    }

    public void uploadEvent() {
        LYSPhotoManagerFragment.lambda$new$0(this.arg$1);
    }
}
