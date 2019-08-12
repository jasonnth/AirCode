package com.airbnb.android.core.views;

import p032rx.functions.Func1;

final /* synthetic */ class OptionsMenuFactory$$Lambda$1 implements Func1 {
    private static final OptionsMenuFactory$$Lambda$1 instance = new OptionsMenuFactory$$Lambda$1();

    private OptionsMenuFactory$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Integer.toString(((Integer) obj).intValue());
    }
}
