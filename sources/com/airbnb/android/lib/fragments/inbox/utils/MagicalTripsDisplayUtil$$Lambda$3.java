package com.airbnb.android.lib.fragments.inbox.utils;

import com.google.common.base.Function;
import java.util.Map;

final /* synthetic */ class MagicalTripsDisplayUtil$$Lambda$3 implements Function {
    private final Map arg$1;

    private MagicalTripsDisplayUtil$$Lambda$3(Map map) {
        this.arg$1 = map;
    }

    public static Function lambdaFactory$(Map map) {
        return new MagicalTripsDisplayUtil$$Lambda$3(map);
    }

    public Object apply(Object obj) {
        return this.arg$1.get((Long) obj);
    }
}
