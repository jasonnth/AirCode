package com.airbnb.android.lib.activities;

import com.google.common.base.Predicate;

final /* synthetic */ class NavigationSection$$Lambda$1 implements Predicate {
    private final int arg$1;

    private NavigationSection$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new NavigationSection$$Lambda$1(i);
    }

    public boolean apply(Object obj) {
        return NavigationSection.lambda$findByItemId$0(this.arg$1, (NavigationSection) obj);
    }
}
