package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationEmailInputFragment$$Lambda$4 implements Action1 {
    private final AccountVerificationEmailInputFragment arg$1;

    private AccountVerificationEmailInputFragment$$Lambda$4(AccountVerificationEmailInputFragment accountVerificationEmailInputFragment) {
        this.arg$1 = accountVerificationEmailInputFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationEmailInputFragment accountVerificationEmailInputFragment) {
        return new AccountVerificationEmailInputFragment$$Lambda$4(accountVerificationEmailInputFragment);
    }

    public void call(Object obj) {
        AccountVerificationEmailInputFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
