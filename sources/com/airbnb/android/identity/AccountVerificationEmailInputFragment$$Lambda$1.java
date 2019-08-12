package com.airbnb.android.identity;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationEmailInputFragment$$Lambda$1 implements Action1 {
    private final AccountVerificationEmailInputFragment arg$1;

    private AccountVerificationEmailInputFragment$$Lambda$1(AccountVerificationEmailInputFragment accountVerificationEmailInputFragment) {
        this.arg$1 = accountVerificationEmailInputFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationEmailInputFragment accountVerificationEmailInputFragment) {
        return new AccountVerificationEmailInputFragment$$Lambda$1(accountVerificationEmailInputFragment);
    }

    public void call(Object obj) {
        AccountVerificationEmailInputFragment.lambda$new$0(this.arg$1, (BaseResponse) obj);
    }
}
