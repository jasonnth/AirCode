package com.airbnb.android.superhero;

import p032rx.functions.Action0;

final /* synthetic */ class SuperHeroManagerImpl$$Lambda$5 implements Action0 {
    private final SuperHeroManagerImpl arg$1;

    private SuperHeroManagerImpl$$Lambda$5(SuperHeroManagerImpl superHeroManagerImpl) {
        this.arg$1 = superHeroManagerImpl;
    }

    public static Action0 lambdaFactory$(SuperHeroManagerImpl superHeroManagerImpl) {
        return new SuperHeroManagerImpl$$Lambda$5(superHeroManagerImpl);
    }

    public void call() {
        this.arg$1.onMessagesUpdatedOrError();
    }
}
