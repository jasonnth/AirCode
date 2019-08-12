package com.airbnb.android.identity;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class AccountVerificationProfilePhotoFragment$$Lambda$5 implements Predicate {
    private static final AccountVerificationProfilePhotoFragment$$Lambda$5 instance = new AccountVerificationProfilePhotoFragment$$Lambda$5();

    private AccountVerificationProfilePhotoFragment$$Lambda$5() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return AccountVerificationProfilePhotoFragment.lambda$null$2((AccountVerification) obj);
    }
}
