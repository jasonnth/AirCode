package com.airbnb.android.superhero;

import java.util.Collection;

final /* synthetic */ class SuperHeroThreadFragment$$Lambda$1 implements Runnable {
    private final SuperHeroThreadFragment arg$1;
    private final Collection arg$2;

    private SuperHeroThreadFragment$$Lambda$1(SuperHeroThreadFragment superHeroThreadFragment, Collection collection) {
        this.arg$1 = superHeroThreadFragment;
        this.arg$2 = collection;
    }

    public static Runnable lambdaFactory$(SuperHeroThreadFragment superHeroThreadFragment, Collection collection) {
        return new SuperHeroThreadFragment$$Lambda$1(superHeroThreadFragment, collection);
    }

    public void run() {
        this.arg$1.jitneyLogger.showHeroThread(this.arg$2);
    }
}
