package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class MessagingSyncs$$Lambda$1 implements Function {
    private static final MessagingSyncs$$Lambda$1 instance = new MessagingSyncs$$Lambda$1();

    private MessagingSyncs$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((Thread) obj).getId());
    }
}
