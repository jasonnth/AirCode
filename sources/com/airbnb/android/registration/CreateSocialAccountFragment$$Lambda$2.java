package com.airbnb.android.registration;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CreateSocialAccountFragment$$Lambda$2 implements Action1 {
    private final CreateSocialAccountFragment arg$1;

    private CreateSocialAccountFragment$$Lambda$2(CreateSocialAccountFragment createSocialAccountFragment) {
        this.arg$1 = createSocialAccountFragment;
    }

    public static Action1 lambdaFactory$(CreateSocialAccountFragment createSocialAccountFragment) {
        return new CreateSocialAccountFragment$$Lambda$2(createSocialAccountFragment);
    }

    public void call(Object obj) {
        CreateSocialAccountFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
