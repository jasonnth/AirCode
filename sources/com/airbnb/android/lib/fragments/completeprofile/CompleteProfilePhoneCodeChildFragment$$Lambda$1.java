package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.android.core.responses.AccountResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CompleteProfilePhoneCodeChildFragment$$Lambda$1 implements Action1 {
    private final CompleteProfilePhoneCodeChildFragment arg$1;

    private CompleteProfilePhoneCodeChildFragment$$Lambda$1(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        this.arg$1 = completeProfilePhoneCodeChildFragment;
    }

    public static Action1 lambdaFactory$(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        return new CompleteProfilePhoneCodeChildFragment$$Lambda$1(completeProfilePhoneCodeChildFragment);
    }

    public void call(Object obj) {
        CompleteProfilePhoneCodeChildFragment.lambda$new$4(this.arg$1, (AccountResponse) obj);
    }
}
