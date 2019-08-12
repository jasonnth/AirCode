package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSPhotoDetailFragment$$Lambda$4 implements Action1 {
    private final LYSPhotoDetailFragment arg$1;

    private LYSPhotoDetailFragment$$Lambda$4(LYSPhotoDetailFragment lYSPhotoDetailFragment) {
        this.arg$1 = lYSPhotoDetailFragment;
    }

    public static Action1 lambdaFactory$(LYSPhotoDetailFragment lYSPhotoDetailFragment) {
        return new LYSPhotoDetailFragment$$Lambda$4(lYSPhotoDetailFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleError((AirRequestNetworkException) obj);
    }
}
