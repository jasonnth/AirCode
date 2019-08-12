package com.airbnb.android.identity;

import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationEmailConfirmationFragment$$Lambda$4 implements Action1 {
    private final AccountVerificationEmailConfirmationFragment arg$1;

    private AccountVerificationEmailConfirmationFragment$$Lambda$4(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment) {
        this.arg$1 = accountVerificationEmailConfirmationFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment) {
        return new AccountVerificationEmailConfirmationFragment$$Lambda$4(accountVerificationEmailConfirmationFragment);
    }

    public void call(Object obj) {
        this.arg$1.delayStartPolling();
    }
}
