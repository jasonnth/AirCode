package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSPhotoDetailFragment$$Lambda$1 implements Action1 {
    private final LYSPhotoDetailFragment arg$1;

    private LYSPhotoDetailFragment$$Lambda$1(LYSPhotoDetailFragment lYSPhotoDetailFragment) {
        this.arg$1 = lYSPhotoDetailFragment;
    }

    public static Action1 lambdaFactory$(LYSPhotoDetailFragment lYSPhotoDetailFragment) {
        return new LYSPhotoDetailFragment$$Lambda$1(lYSPhotoDetailFragment);
    }

    public void call(Object obj) {
        LYSPhotoDetailFragment.lambda$new$0(this.arg$1, (BaseResponse) obj);
    }
}