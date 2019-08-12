package com.airbnb.android.superhero;

import java.util.concurrent.Callable;

final /* synthetic */ class SuperHeroDataController$$Lambda$3 implements Callable {
    private final SuperHeroDataController arg$1;

    private SuperHeroDataController$$Lambda$3(SuperHeroDataController superHeroDataController) {
        this.arg$1 = superHeroDataController;
    }

    public static Callable lambdaFactory$(SuperHeroDataController superHeroDataController) {
        return new SuperHeroDataController$$Lambda$3(superHeroDataController);
    }

    public Object call() {
        return this.arg$1.tableOpenHelper.messagesToPresent();
    }
}
