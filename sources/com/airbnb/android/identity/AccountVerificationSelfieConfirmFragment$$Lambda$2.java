package com.airbnb.android.identity;

import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationSelfieConfirmFragment$$Lambda$2 implements Action1 {
    private final AccountVerificationSelfieConfirmFragment arg$1;

    private AccountVerificationSelfieConfirmFragment$$Lambda$2(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment) {
        this.arg$1 = accountVerificationSelfieConfirmFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment) {
        return new AccountVerificationSelfieConfirmFragment$$Lambda$2(accountVerificationSelfieConfirmFragment);
    }

    public void call(Object obj) {
        AccountVerificationSelfieConfirmFragment.lambda$new$1(this.arg$1, (Boolean) obj);
    }
}
