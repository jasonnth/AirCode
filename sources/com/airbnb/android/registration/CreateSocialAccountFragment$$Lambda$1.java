package com.airbnb.android.registration;

import com.airbnb.android.registration.responses.SocialSignupResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CreateSocialAccountFragment$$Lambda$1 implements Action1 {
    private final CreateSocialAccountFragment arg$1;

    private CreateSocialAccountFragment$$Lambda$1(CreateSocialAccountFragment createSocialAccountFragment) {
        this.arg$1 = createSocialAccountFragment;
    }

    public static Action1 lambdaFactory$(CreateSocialAccountFragment createSocialAccountFragment) {
        return new CreateSocialAccountFragment$$Lambda$1(createSocialAccountFragment);
    }

    public void call(Object obj) {
        CreateSocialAccountFragment.lambda$new$0(this.arg$1, (SocialSignupResponse) obj);
    }
}
