package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CreateGuestIdentityFragment$$Lambda$2 implements Action1 {
    private final CreateGuestIdentityFragment arg$1;

    private CreateGuestIdentityFragment$$Lambda$2(CreateGuestIdentityFragment createGuestIdentityFragment) {
        this.arg$1 = createGuestIdentityFragment;
    }

    public static Action1 lambdaFactory$(CreateGuestIdentityFragment createGuestIdentityFragment) {
        return new CreateGuestIdentityFragment$$Lambda$2(createGuestIdentityFragment);
    }

    public void call(Object obj) {
        CreateGuestIdentityFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
