package com.airbnb.android.lib.fragments;

import com.google.common.base.Predicate;

final /* synthetic */ class UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$2 implements Predicate {
    private static final UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$2 instance = new UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$2();

    private UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return VerificationsAdapter.lambda$new$1((String) obj);
    }
}
