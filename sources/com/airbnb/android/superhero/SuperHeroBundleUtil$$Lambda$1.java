package com.airbnb.android.superhero;

import com.google.common.base.Function;

final /* synthetic */ class SuperHeroBundleUtil$$Lambda$1 implements Function {
    private static final SuperHeroBundleUtil$$Lambda$1 instance = new SuperHeroBundleUtil$$Lambda$1();

    private SuperHeroBundleUtil$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((SuperHeroAction) obj).toBundle();
    }
}
