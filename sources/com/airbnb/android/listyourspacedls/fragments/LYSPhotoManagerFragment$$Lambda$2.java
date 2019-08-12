package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSPhotoManagerFragment$$Lambda$2 implements Action1 {
    private final LYSPhotoManagerFragment arg$1;

    private LYSPhotoManagerFragment$$Lambda$2(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        this.arg$1 = lYSPhotoManagerFragment;
    }

    public static Action1 lambdaFactory$(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        return new LYSPhotoManagerFragment$$Lambda$2(lYSPhotoManagerFragment);
    }

    public void call(Object obj) {
        LYSPhotoManagerFragment.lambda$new$1(this.arg$1, (SimpleListingResponse) obj);
    }
}
