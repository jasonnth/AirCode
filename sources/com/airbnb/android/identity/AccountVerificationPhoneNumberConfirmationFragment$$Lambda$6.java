package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationPhoneNumberConfirmationFragment$$Lambda$6 implements Action1 {
    private final AccountVerificationPhoneNumberConfirmationFragment arg$1;

    private AccountVerificationPhoneNumberConfirmationFragment$$Lambda$6(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment) {
        this.arg$1 = accountVerificationPhoneNumberConfirmationFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment) {
        return new AccountVerificationPhoneNumberConfirmationFragment$$Lambda$6(accountVerificationPhoneNumberConfirmationFragment);
    }

    public void call(Object obj) {
        AccountVerificationPhoneNumberConfirmationFragment.lambda$new$5(this.arg$1, (AirRequestNetworkException) obj);
    }
}
