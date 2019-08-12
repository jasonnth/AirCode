package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.android.core.responses.UserWrapperResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoVerificationFragment$$Lambda$1 implements Action1 {
    private final PhotoVerificationFragment arg$1;

    private PhotoVerificationFragment$$Lambda$1(PhotoVerificationFragment photoVerificationFragment) {
        this.arg$1 = photoVerificationFragment;
    }

    public static Action1 lambdaFactory$(PhotoVerificationFragment photoVerificationFragment) {
        return new PhotoVerificationFragment$$Lambda$1(photoVerificationFragment);
    }

    public void call(Object obj) {
        PhotoVerificationFragment.lambda$new$0(this.arg$1, (UserWrapperResponse) obj);
    }
}
