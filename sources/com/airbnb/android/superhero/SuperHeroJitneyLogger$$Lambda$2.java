package com.airbnb.android.superhero;

import com.google.common.base.Function;

final /* synthetic */ class SuperHeroJitneyLogger$$Lambda$2 implements Function {
    private static final SuperHeroJitneyLogger$$Lambda$2 instance = new SuperHeroJitneyLogger$$Lambda$2();

    private SuperHeroJitneyLogger$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((SuperHeroAction) obj).text();
    }
}
