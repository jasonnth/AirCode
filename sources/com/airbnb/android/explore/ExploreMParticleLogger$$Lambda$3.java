package com.airbnb.android.explore;

import com.airbnb.android.core.models.SearchResult;
import com.google.common.base.Function;

final /* synthetic */ class ExploreMParticleLogger$$Lambda$3 implements Function {
    private static final ExploreMParticleLogger$$Lambda$3 instance = new ExploreMParticleLogger$$Lambda$3();

    private ExploreMParticleLogger$$Lambda$3() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((SearchResult) obj).getListing();
    }
}
