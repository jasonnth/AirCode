package com.airbnb.android.listyourspacedls.adapters;

import com.airbnb.android.core.enums.PropertyType;
import p032rx.functions.Func1;

final /* synthetic */ class SpaceTypeAdapter$$Lambda$2 implements Func1 {
    private static final SpaceTypeAdapter$$Lambda$2 instance = new SpaceTypeAdapter$$Lambda$2();

    private SpaceTypeAdapter$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Integer.valueOf(((PropertyType) obj).titleId);
    }
}
