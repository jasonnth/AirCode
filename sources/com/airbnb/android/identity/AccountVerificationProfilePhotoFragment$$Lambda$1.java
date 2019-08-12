package com.airbnb.android.identity;

import com.airbnb.android.core.responses.UserWrapperResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationProfilePhotoFragment$$Lambda$1 implements Action1 {
    private final AccountVerificationProfilePhotoFragment arg$1;

    private AccountVerificationProfilePhotoFragment$$Lambda$1(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment) {
        this.arg$1 = accountVerificationProfilePhotoFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment) {
        return new AccountVerificationProfilePhotoFragment$$Lambda$1(accountVerificationProfilePhotoFragment);
    }

    public void call(Object obj) {
        AccountVerificationProfilePhotoFragment.lambda$new$0(this.arg$1, (UserWrapperResponse) obj);
    }
}
