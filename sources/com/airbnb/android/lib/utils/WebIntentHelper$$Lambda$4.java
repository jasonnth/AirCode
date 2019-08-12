package com.airbnb.android.lib.utils;

import com.google.common.base.Function;

final /* synthetic */ class WebIntentHelper$$Lambda$4 implements Function {
    private static final WebIntentHelper$$Lambda$4 instance = new WebIntentHelper$$Lambda$4();

    private WebIntentHelper$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Integer.valueOf((String) obj);
    }
}
