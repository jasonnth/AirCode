package com.airbnb.android.identity;

import com.airbnb.android.core.responses.AccountVerificationsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationProfilePhotoFragment$$Lambda$3 implements Action1 {
    private final AccountVerificationProfilePhotoFragment arg$1;

    private AccountVerificationProfilePhotoFragment$$Lambda$3(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment) {
        this.arg$1 = accountVerificationProfilePhotoFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment) {
        return new AccountVerificationProfilePhotoFragment$$Lambda$3(accountVerificationProfilePhotoFragment);
    }

    public void call(Object obj) {
        AccountVerificationProfilePhotoFragment.lambda$new$3(this.arg$1, (AccountVerificationsResponse) obj);
    }
}
