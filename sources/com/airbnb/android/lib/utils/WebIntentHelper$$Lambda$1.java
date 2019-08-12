package com.airbnb.android.lib.utils;

import com.google.common.base.Function;

final /* synthetic */ class WebIntentHelper$$Lambda$1 implements Function {
    private static final WebIntentHelper$$Lambda$1 instance = new WebIntentHelper$$Lambda$1();

    private WebIntentHelper$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((String) obj).replace("+", " ");
    }
}
