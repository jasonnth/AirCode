package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSPhotoManagerFragment$$Lambda$3 implements Action1 {
    private final LYSPhotoManagerFragment arg$1;

    private LYSPhotoManagerFragment$$Lambda$3(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        this.arg$1 = lYSPhotoManagerFragment;
    }

    public static Action1 lambdaFactory$(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        return new LYSPhotoManagerFragment$$Lambda$3(lYSPhotoManagerFragment);
    }

    public void call(Object obj) {
        LYSPhotoManagerFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
