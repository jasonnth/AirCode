package com.airbnb.android.react;

import com.google.common.base.Function;

final /* synthetic */ class I18nModule$$Lambda$4 implements Function {
    private final I18nModule arg$1;

    private I18nModule$$Lambda$4(I18nModule i18nModule) {
        this.arg$1 = i18nModule;
    }

    public static Function lambdaFactory$(I18nModule i18nModule) {
        return new I18nModule$$Lambda$4(i18nModule);
    }

    public Object apply(Object obj) {
        return this.arg$1.fieldNameToString((String) obj);
    }
}
