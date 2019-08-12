package com.airbnb.android.lib.activities;

import com.airbnb.android.core.models.HomeCollectionApplication;
import com.google.common.base.Predicate;

final /* synthetic */ class HomeActivity$2$$Lambda$1 implements Predicate {
    private static final HomeActivity$2$$Lambda$1 instance = new HomeActivity$2$$Lambda$1();

    private HomeActivity$2$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return C66392.lambda$onResponse$0((HomeCollectionApplication) obj);
    }
}
