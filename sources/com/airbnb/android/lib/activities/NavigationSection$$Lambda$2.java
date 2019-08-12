package com.airbnb.android.lib.activities;

import com.google.common.base.Predicate;

final /* synthetic */ class NavigationSection$$Lambda$2 implements Predicate {
    private final String arg$1;

    private NavigationSection$$Lambda$2(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new NavigationSection$$Lambda$2(str);
    }

    public boolean apply(Object obj) {
        return ((NavigationSection) obj).appTab.equals(this.arg$1);
    }
}
