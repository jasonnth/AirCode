package com.airbnb.android.core.column_adapters;

import com.airbnb.android.core.CoreApplication;
import dagger.Lazy;

final /* synthetic */ class JsonColumnAdapter$$Lambda$1 implements Lazy {
    private static final JsonColumnAdapter$$Lambda$1 instance = new JsonColumnAdapter$$Lambda$1();

    private JsonColumnAdapter$$Lambda$1() {
    }

    public static Lazy lambdaFactory$() {
        return instance;
    }

    public Object get() {
        return CoreApplication.instance().component().objectMapper();
    }
}
