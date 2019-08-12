package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class Thread$$Lambda$2 implements Function {
    private static final Thread$$Lambda$2 instance = new Thread$$Lambda$2();

    private Thread$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((Post) obj).getId());
    }
}
