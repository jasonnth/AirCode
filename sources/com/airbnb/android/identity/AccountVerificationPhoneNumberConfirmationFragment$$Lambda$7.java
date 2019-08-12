package com.airbnb.android.identity;

import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationPhoneNumberConfirmationFragment$$Lambda$7 implements Action1 {
    private final AccountVerificationPhoneNumberConfirmationFragment arg$1;

    private AccountVerificationPhoneNumberConfirmationFragment$$Lambda$7(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment) {
        this.arg$1 = accountVerificationPhoneNumberConfirmationFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment) {
        return new AccountVerificationPhoneNumberConfirmationFragment$$Lambda$7(accountVerificationPhoneNumberConfirmationFragment);
    }

    public void call(Object obj) {
        AccountVerificationPhoneNumberConfirmationFragment.lambda$onViewCreated$6(this.arg$1, (String) obj);
    }
}
