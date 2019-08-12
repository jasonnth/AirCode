package com.airbnb.android.lib.fragments.inbox.utils;

import com.airbnb.android.core.models.User;
import com.google.common.base.Function;

final /* synthetic */ class MagicalTripsDisplayUtil$$Lambda$1 implements Function {
    private static final MagicalTripsDisplayUtil$$Lambda$1 instance = new MagicalTripsDisplayUtil$$Lambda$1();

    private MagicalTripsDisplayUtil$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((User) obj).getId());
    }
}
