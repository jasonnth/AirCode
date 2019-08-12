package com.airbnb.android.lib.fragments;

import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CreateGuestIdentityFragment$$Lambda$3 implements LinkOnClickListener {
    private final CreateGuestIdentityFragment arg$1;

    private CreateGuestIdentityFragment$$Lambda$3(CreateGuestIdentityFragment createGuestIdentityFragment) {
        this.arg$1 = createGuestIdentityFragment;
    }

    public static LinkOnClickListener lambdaFactory$(CreateGuestIdentityFragment createGuestIdentityFragment) {
        return new CreateGuestIdentityFragment$$Lambda$3(createGuestIdentityFragment);
    }

    public void onClickLink(int i) {
        CreateGuestIdentityFragment.lambda$onCreateView$0(this.arg$1, i);
    }
}
