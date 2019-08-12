package com.airbnb.android.core.column_adapters;

import com.airbnb.android.utils.JacksonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Lazy;
import java.lang.reflect.Type;
import javax.inject.Provider;

final /* synthetic */ class JsonColumnAdapter$$Lambda$3 implements Provider {
    private final Lazy arg$1;
    private final Type arg$2;

    private JsonColumnAdapter$$Lambda$3(Lazy lazy, Type type) {
        this.arg$1 = lazy;
        this.arg$2 = type;
    }

    public static Provider lambdaFactory$(Lazy lazy, Type type) {
        return new JsonColumnAdapter$$Lambda$3(lazy, type);
    }

    public Object get() {
        return JacksonUtils.writerForType((ObjectMapper) this.arg$1.get(), this.arg$2);
    }
}
