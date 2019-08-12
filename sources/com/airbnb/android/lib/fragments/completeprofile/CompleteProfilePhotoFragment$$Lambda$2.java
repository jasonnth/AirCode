package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CompleteProfilePhotoFragment$$Lambda$2 implements Action1 {
    private final CompleteProfilePhotoFragment arg$1;

    private CompleteProfilePhotoFragment$$Lambda$2(CompleteProfilePhotoFragment completeProfilePhotoFragment) {
        this.arg$1 = completeProfilePhotoFragment;
    }

    public static Action1 lambdaFactory$(CompleteProfilePhotoFragment completeProfilePhotoFragment) {
        return new CompleteProfilePhotoFragment$$Lambda$2(completeProfilePhotoFragment);
    }

    public void call(Object obj) {
        CompleteProfilePhotoFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
