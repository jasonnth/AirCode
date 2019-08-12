package com.airbnb.android.lib.utils;

import com.airbnb.android.core.models.C6120RoomType;
import com.google.common.base.Predicate;

final /* synthetic */ class WebIntentHelper$$Lambda$3 implements Predicate {
    private static final WebIntentHelper$$Lambda$3 instance = new WebIntentHelper$$Lambda$3();

    private WebIntentHelper$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return WebIntentHelper.lambda$parseRoomTypes$1((C6120RoomType) obj);
    }
}
