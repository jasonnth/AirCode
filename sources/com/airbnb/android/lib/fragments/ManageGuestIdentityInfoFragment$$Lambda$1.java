package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageGuestIdentityInfoFragment$$Lambda$1 implements Action1 {
    private final ManageGuestIdentityInfoFragment arg$1;

    private ManageGuestIdentityInfoFragment$$Lambda$1(ManageGuestIdentityInfoFragment manageGuestIdentityInfoFragment) {
        this.arg$1 = manageGuestIdentityInfoFragment;
    }

    public static Action1 lambdaFactory$(ManageGuestIdentityInfoFragment manageGuestIdentityInfoFragment) {
        return new ManageGuestIdentityInfoFragment$$Lambda$1(manageGuestIdentityInfoFragment);
    }

    public void call(Object obj) {
        ManageGuestIdentityInfoFragment.lambda$new$0(this.arg$1, (BaseResponse) obj);
    }
}
