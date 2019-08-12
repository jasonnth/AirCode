package com.airbnb.android.explore;

import com.airbnb.android.core.models.ExploreSection;
import com.google.common.base.Function;

final /* synthetic */ class ExploreMParticleLogger$$Lambda$2 implements Function {
    private static final ExploreMParticleLogger$$Lambda$2 instance = new ExploreMParticleLogger$$Lambda$2();

    private ExploreMParticleLogger$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((ExploreSection) obj).getListings();
    }
}
