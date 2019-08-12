package com.airbnb.android.identity;

import com.airbnb.android.core.responses.AccountVerificationsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationEmailConfirmationFragment$$Lambda$3 implements Action1 {
    private final AccountVerificationEmailConfirmationFragment arg$1;

    private AccountVerificationEmailConfirmationFragment$$Lambda$3(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment) {
        this.arg$1 = accountVerificationEmailConfirmationFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment) {
        return new AccountVerificationEmailConfirmationFragment$$Lambda$3(accountVerificationEmailConfirmationFragment);
    }

    public void call(Object obj) {
        AccountVerificationEmailConfirmationFragment.lambda$new$2(this.arg$1, (AccountVerificationsResponse) obj);
    }
}
