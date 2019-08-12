package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class EmailVerificationFragment$$Lambda$3 implements Action1 {
    private final EmailVerificationFragment arg$1;

    private EmailVerificationFragment$$Lambda$3(EmailVerificationFragment emailVerificationFragment) {
        this.arg$1 = emailVerificationFragment;
    }

    public static Action1 lambdaFactory$(EmailVerificationFragment emailVerificationFragment) {
        return new EmailVerificationFragment$$Lambda$3(emailVerificationFragment);
    }

    public void call(Object obj) {
        NetworkUtil.toastGenericNetworkError(this.arg$1.getActivity());
    }
}
