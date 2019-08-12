package com.airbnb.android.react;

import com.google.common.base.Function;
import java.util.Map;

final /* synthetic */ class NavigatorModule$$Lambda$4 implements Function {
    private final Map arg$1;

    private NavigatorModule$$Lambda$4(Map map) {
        this.arg$1 = map;
    }

    public static Function lambdaFactory$(Map map) {
        return new NavigatorModule$$Lambda$4(map);
    }

    public Object apply(Object obj) {
        return this.arg$1.get((String) obj);
    }
}
