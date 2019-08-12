package com.airbnb.android.explore;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Function;

final /* synthetic */ class ExploreMParticleLogger$$Lambda$4 implements Function {
    private static final ExploreMParticleLogger$$Lambda$4 instance = new ExploreMParticleLogger$$Lambda$4();

    private ExploreMParticleLogger$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((Listing) obj).getId());
    }
}
