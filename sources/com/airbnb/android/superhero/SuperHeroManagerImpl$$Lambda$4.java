package com.airbnb.android.superhero;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class SuperHeroManagerImpl$$Lambda$4 implements Action1 {
    private static final SuperHeroManagerImpl$$Lambda$4 instance = new SuperHeroManagerImpl$$Lambda$4();

    private SuperHeroManagerImpl$$Lambda$4() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        SuperHeroManagerImpl.lambda$new$2((AirRequestNetworkException) obj);
    }
}
