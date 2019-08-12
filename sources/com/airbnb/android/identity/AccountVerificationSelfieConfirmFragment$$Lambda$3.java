package com.airbnb.android.identity;

import com.google.common.base.Predicate;

final /* synthetic */ class AccountVerificationSelfieConfirmFragment$$Lambda$3 implements Predicate {
    private final AccountVerificationSelfieConfirmFragment arg$1;

    private AccountVerificationSelfieConfirmFragment$$Lambda$3(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment) {
        this.arg$1 = accountVerificationSelfieConfirmFragment;
    }

    public static Predicate lambdaFactory$(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment) {
        return new AccountVerificationSelfieConfirmFragment$$Lambda$3(accountVerificationSelfieConfirmFragment);
    }

    public boolean apply(Object obj) {
        return this.arg$1.isPhotoUploading((String) obj);
    }
}
