package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.models.User;
import com.google.common.base.Function;

final /* synthetic */ class ThreadAdapter$$Lambda$1 implements Function {
    private static final ThreadAdapter$$Lambda$1 instance = new ThreadAdapter$$Lambda$1();

    private ThreadAdapter$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((User) obj).getId());
    }
}
