package com.airbnb.android.react;

import com.google.common.base.Function;
import java.lang.reflect.Field;

final /* synthetic */ class I18nModule$$Lambda$2 implements Function {
    private static final I18nModule$$Lambda$2 instance = new I18nModule$$Lambda$2();

    private I18nModule$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((Field) obj).getName();
    }
}
