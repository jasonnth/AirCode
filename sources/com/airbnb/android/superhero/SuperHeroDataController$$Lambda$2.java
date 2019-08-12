package com.airbnb.android.superhero;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class SuperHeroDataController$$Lambda$2 implements Action1 {
    private static final SuperHeroDataController$$Lambda$2 instance = new SuperHeroDataController$$Lambda$2();

    private SuperHeroDataController$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        SuperHeroDataController.lambda$new$4((AirRequestNetworkException) obj);
    }
}
