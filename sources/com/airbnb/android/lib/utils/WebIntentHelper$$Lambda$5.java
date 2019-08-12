package com.airbnb.android.lib.utils;

import com.airbnb.android.core.p008mt.models.PrimaryCategory;
import com.google.common.base.Function;

final /* synthetic */ class WebIntentHelper$$Lambda$5 implements Function {
    private static final WebIntentHelper$$Lambda$5 instance = new WebIntentHelper$$Lambda$5();

    private WebIntentHelper$$Lambda$5() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PrimaryCategory.from(((Integer) obj).intValue());
    }
}
