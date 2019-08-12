package com.airbnb.android.explore;

import com.airbnb.android.core.models.ExploreSection;
import com.google.common.base.Predicate;

final /* synthetic */ class ExploreMParticleLogger$$Lambda$1 implements Predicate {
    private static final ExploreMParticleLogger$$Lambda$1 instance = new ExploreMParticleLogger$$Lambda$1();

    private ExploreMParticleLogger$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ExploreMParticleLogger.lambda$homeImpression$0((ExploreSection) obj);
    }
}
