package com.airbnb.android.superhero;

import p032rx.functions.Action1;

final /* synthetic */ class SuperHeroDataController$$Lambda$6 implements Action1 {
    private final SuperHeroDataController arg$1;

    private SuperHeroDataController$$Lambda$6(SuperHeroDataController superHeroDataController) {
        this.arg$1 = superHeroDataController;
    }

    public static Action1 lambdaFactory$(SuperHeroDataController superHeroDataController) {
        return new SuperHeroDataController$$Lambda$6(superHeroDataController);
    }

    public void call(Object obj) {
        this.arg$1.superHeroManager.markMessageAsPresented(((SuperHeroMessage) obj).mo11531id());
    }
}