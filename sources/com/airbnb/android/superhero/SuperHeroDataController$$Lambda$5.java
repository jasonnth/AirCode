package com.airbnb.android.superhero;

import p032rx.functions.Func1;

final /* synthetic */ class SuperHeroDataController$$Lambda$5 implements Func1 {
    private static final SuperHeroDataController$$Lambda$5 instance = new SuperHeroDataController$$Lambda$5();

    private SuperHeroDataController$$Lambda$5() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SuperHeroDataController.lambda$handleDatabaseResults$1((SuperHeroMessage) obj);
    }
}
