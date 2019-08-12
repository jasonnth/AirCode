package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class User$$Lambda$1 implements Predicate {
    private static final User$$Lambda$1 instance = new User$$Lambda$1();

    private User$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((ProfilePhoneNumber) obj).isVerified();
    }
}
