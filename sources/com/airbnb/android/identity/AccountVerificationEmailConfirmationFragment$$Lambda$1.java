package com.airbnb.android.identity;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationEmailConfirmationFragment$$Lambda$1 implements Action1 {
    private final AccountVerificationEmailConfirmationFragment arg$1;

    private AccountVerificationEmailConfirmationFragment$$Lambda$1(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment) {
        this.arg$1 = accountVerificationEmailConfirmationFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment) {
        return new AccountVerificationEmailConfirmationFragment$$Lambda$1(accountVerificationEmailConfirmationFragment);
    }

    public void call(Object obj) {
        AccountVerificationEmailConfirmationFragment.lambda$new$0(this.arg$1, (BaseResponse) obj);
    }
}
