package com.airbnb.android.superhero;

import com.google.common.base.Function;

final /* synthetic */ class SuperHeroJitneyLogger$$Lambda$1 implements Function {
    private final SuperHeroJitneyLogger arg$1;

    private SuperHeroJitneyLogger$$Lambda$1(SuperHeroJitneyLogger superHeroJitneyLogger) {
        this.arg$1 = superHeroJitneyLogger;
    }

    public static Function lambdaFactory$(SuperHeroJitneyLogger superHeroJitneyLogger) {
        return new SuperHeroJitneyLogger$$Lambda$1(superHeroJitneyLogger);
    }

    public Object apply(Object obj) {
        return this.arg$1.getHeroContext((SuperHeroMessage) obj);
    }
}
