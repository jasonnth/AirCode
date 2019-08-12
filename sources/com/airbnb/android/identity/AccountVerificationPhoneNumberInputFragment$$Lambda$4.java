package com.airbnb.android.identity;

import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationPhoneNumberInputFragment$$Lambda$4 implements Action1 {
    private final AccountVerificationPhoneNumberInputFragment arg$1;

    private AccountVerificationPhoneNumberInputFragment$$Lambda$4(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment) {
        this.arg$1 = accountVerificationPhoneNumberInputFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment) {
        return new AccountVerificationPhoneNumberInputFragment$$Lambda$4(accountVerificationPhoneNumberInputFragment);
    }

    public void call(Object obj) {
        this.arg$1.doSubmit();
    }
}
