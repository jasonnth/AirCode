package com.airbnb.android.superhero;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class SuperHeroManagerImpl$$Lambda$2 implements Action1 {
    private final SuperHeroManagerImpl arg$1;

    private SuperHeroManagerImpl$$Lambda$2(SuperHeroManagerImpl superHeroManagerImpl) {
        this.arg$1 = superHeroManagerImpl;
    }

    public static Action1 lambdaFactory$(SuperHeroManagerImpl superHeroManagerImpl) {
        return new SuperHeroManagerImpl$$Lambda$2(superHeroManagerImpl);
    }

    public void call(Object obj) {
        SuperHeroManagerImpl.lambda$new$0(this.arg$1, (AirRequestNetworkException) obj);
    }
}
