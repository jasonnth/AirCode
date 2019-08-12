package com.airbnb.android.lib.utils;

import com.airbnb.android.core.models.C6120RoomType;
import com.google.common.base.Function;

final /* synthetic */ class WebIntentHelper$$Lambda$2 implements Function {
    private static final WebIntentHelper$$Lambda$2 instance = new WebIntentHelper$$Lambda$2();

    private WebIntentHelper$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return C6120RoomType.fromKey((String) obj);
    }
}
