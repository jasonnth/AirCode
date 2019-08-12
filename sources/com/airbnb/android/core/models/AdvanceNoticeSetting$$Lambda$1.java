package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class AdvanceNoticeSetting$$Lambda$1 implements Function {
    private static final AdvanceNoticeSetting$$Lambda$1 instance = new AdvanceNoticeSetting$$Lambda$1();

    private AdvanceNoticeSetting$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new AdvanceNoticeSetting(((Integer) obj).intValue());
    }
}
