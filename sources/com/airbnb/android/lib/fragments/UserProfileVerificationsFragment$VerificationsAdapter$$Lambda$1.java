package com.airbnb.android.lib.fragments;

import com.google.common.base.Predicate;

final /* synthetic */ class UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$1 implements Predicate {
    private static final UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$1 instance = new UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$1();

    private UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return VerificationsAdapter.lambda$new$0((String) obj);
    }
}
