package com.airbnb.android.core.utils;

import com.airbnb.android.core.models.User;
import com.google.common.base.Function;

final /* synthetic */ class UserUtils$$Lambda$1 implements Function {
    private static final UserUtils$$Lambda$1 instance = new UserUtils$$Lambda$1();

    private UserUtils$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((User) obj).getName();
    }
}
