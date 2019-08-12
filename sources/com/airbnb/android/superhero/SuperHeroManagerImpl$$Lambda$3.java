package com.airbnb.android.superhero;

import p032rx.functions.Action1;

final /* synthetic */ class SuperHeroManagerImpl$$Lambda$3 implements Action1 {
    private final SuperHeroManagerImpl arg$1;

    private SuperHeroManagerImpl$$Lambda$3(SuperHeroManagerImpl superHeroManagerImpl) {
        this.arg$1 = superHeroManagerImpl;
    }

    public static Action1 lambdaFactory$(SuperHeroManagerImpl superHeroManagerImpl) {
        return new SuperHeroManagerImpl$$Lambda$3(superHeroManagerImpl);
    }

    public void call(Object obj) {
        SuperHeroManagerImpl.lambda$new$1(this.arg$1, (SuperHeroMessageResponse) obj);
    }
}
