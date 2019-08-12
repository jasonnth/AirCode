package com.airbnb.android.react;

import com.google.common.base.Predicate;

final /* synthetic */ class ReactNavigationCoordinator$$Lambda$1 implements Predicate {
    private final String arg$1;

    private ReactNavigationCoordinator$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new ReactNavigationCoordinator$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((ReactExposedActivityParams) obj).key().equals(this.arg$1);
    }
}
