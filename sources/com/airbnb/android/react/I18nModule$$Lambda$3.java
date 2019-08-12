package com.airbnb.android.react;

import com.google.common.base.Predicate;

final /* synthetic */ class I18nModule$$Lambda$3 implements Predicate {
    private static final I18nModule$$Lambda$3 instance = new I18nModule$$Lambda$3();

    private I18nModule$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((String) obj).startsWith(I18nModule.KEY_PREFIX);
    }
}
