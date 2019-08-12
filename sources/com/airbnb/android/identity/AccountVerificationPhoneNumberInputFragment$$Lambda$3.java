package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationPhoneNumberInputFragment$$Lambda$3 implements Action1 {
    private final AccountVerificationPhoneNumberInputFragment arg$1;

    private AccountVerificationPhoneNumberInputFragment$$Lambda$3(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment) {
        this.arg$1 = accountVerificationPhoneNumberInputFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment) {
        return new AccountVerificationPhoneNumberInputFragment$$Lambda$3(accountVerificationPhoneNumberInputFragment);
    }

    public void call(Object obj) {
        AccountVerificationPhoneNumberInputFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
