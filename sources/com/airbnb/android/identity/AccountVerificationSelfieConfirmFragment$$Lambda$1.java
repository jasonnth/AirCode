package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationSelfieConfirmFragment$$Lambda$1 implements Action1 {
    private final AccountVerificationSelfieConfirmFragment arg$1;

    private AccountVerificationSelfieConfirmFragment$$Lambda$1(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment) {
        this.arg$1 = accountVerificationSelfieConfirmFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment) {
        return new AccountVerificationSelfieConfirmFragment$$Lambda$1(accountVerificationSelfieConfirmFragment);
    }

    public void call(Object obj) {
        AccountVerificationSelfieConfirmFragment.lambda$new$0(this.arg$1, (AirRequestNetworkException) obj);
    }
}
