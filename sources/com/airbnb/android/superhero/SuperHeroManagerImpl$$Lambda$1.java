package com.airbnb.android.superhero;

import p032rx.functions.Action1;

final /* synthetic */ class SuperHeroManagerImpl$$Lambda$1 implements Action1 {
    private final SuperHeroManagerImpl arg$1;

    private SuperHeroManagerImpl$$Lambda$1(SuperHeroManagerImpl superHeroManagerImpl) {
        this.arg$1 = superHeroManagerImpl;
    }

    public static Action1 lambdaFactory$(SuperHeroManagerImpl superHeroManagerImpl) {
        return new SuperHeroManagerImpl$$Lambda$1(superHeroManagerImpl);
    }

    public void call(Object obj) {
        this.arg$1.onMessagesResponse((SuperHeroMessagesResponse) obj);
    }
}
