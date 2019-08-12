package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.android.core.responses.UserWrapperResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CompleteProfilePhotoFragment$$Lambda$1 implements Action1 {
    private final CompleteProfilePhotoFragment arg$1;

    private CompleteProfilePhotoFragment$$Lambda$1(CompleteProfilePhotoFragment completeProfilePhotoFragment) {
        this.arg$1 = completeProfilePhotoFragment;
    }

    public static Action1 lambdaFactory$(CompleteProfilePhotoFragment completeProfilePhotoFragment) {
        return new CompleteProfilePhotoFragment$$Lambda$1(completeProfilePhotoFragment);
    }

    public void call(Object obj) {
        CompleteProfilePhotoFragment.lambda$new$2(this.arg$1, (UserWrapperResponse) obj);
    }
}
