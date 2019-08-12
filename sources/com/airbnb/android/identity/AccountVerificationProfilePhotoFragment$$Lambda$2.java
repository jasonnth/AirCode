package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationProfilePhotoFragment$$Lambda$2 implements Action1 {
    private final AccountVerificationProfilePhotoFragment arg$1;

    private AccountVerificationProfilePhotoFragment$$Lambda$2(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment) {
        this.arg$1 = accountVerificationProfilePhotoFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment) {
        return new AccountVerificationProfilePhotoFragment$$Lambda$2(accountVerificationProfilePhotoFragment);
    }

    public void call(Object obj) {
        AccountVerificationProfilePhotoFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
