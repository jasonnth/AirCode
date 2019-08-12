package com.airbnb.android.explore;

import com.google.common.base.Function;

final /* synthetic */ class ExploreMParticleLogger$$Lambda$5 implements Function {
    private static final ExploreMParticleLogger$$Lambda$5 instance = new ExploreMParticleLogger$$Lambda$5();

    private ExploreMParticleLogger$$Lambda$5() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return String.valueOf((Long) obj);
    }
}
