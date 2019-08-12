package com.airbnb.android.superhero;

import p032rx.functions.Action0;

final /* synthetic */ class SuperHeroScheduler$$Lambda$1 implements Action0 {
    private static final SuperHeroScheduler$$Lambda$1 instance = new SuperHeroScheduler$$Lambda$1();

    private SuperHeroScheduler$$Lambda$1() {
    }

    public static Action0 lambdaFactory$() {
        return instance;
    }

    public void call() {
        SuperHeroScheduler.lambda$persistAndScheduleMessages$0();
    }
}
