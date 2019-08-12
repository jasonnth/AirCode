package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.SaveGuestIdentityInformationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CreateGuestIdentityFragment$$Lambda$1 implements Action1 {
    private final CreateGuestIdentityFragment arg$1;

    private CreateGuestIdentityFragment$$Lambda$1(CreateGuestIdentityFragment createGuestIdentityFragment) {
        this.arg$1 = createGuestIdentityFragment;
    }

    public static Action1 lambdaFactory$(CreateGuestIdentityFragment createGuestIdentityFragment) {
        return new CreateGuestIdentityFragment$$Lambda$1(createGuestIdentityFragment);
    }

    public void call(Object obj) {
        CreateGuestIdentityFragment.lambda$new$1(this.arg$1, (SaveGuestIdentityInformationResponse) obj);
    }
}
