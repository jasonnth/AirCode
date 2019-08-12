package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoVerificationFragment$$Lambda$2 implements Action1 {
    private final PhotoVerificationFragment arg$1;

    private PhotoVerificationFragment$$Lambda$2(PhotoVerificationFragment photoVerificationFragment) {
        this.arg$1 = photoVerificationFragment;
    }

    public static Action1 lambdaFactory$(PhotoVerificationFragment photoVerificationFragment) {
        return new PhotoVerificationFragment$$Lambda$2(photoVerificationFragment);
    }

    public void call(Object obj) {
        PhotoVerificationFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
