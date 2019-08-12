package com.airbnb.android.core.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DataModule_ProvideObjectMapperFactory implements Factory<ObjectMapper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DataModule_ProvideObjectMapperFactory.class.desiredAssertionStatus());
    private final DataModule module;

    public DataModule_ProvideObjectMapperFactory(DataModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public ObjectMapper get() {
        return (ObjectMapper) Preconditions.checkNotNull(this.module.provideObjectMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ObjectMapper> create(DataModule module2) {
        return new DataModule_ProvideObjectMapperFactory(module2);
    }

    public static ObjectMapper proxyProvideObjectMapper(DataModule instance) {
        return instance.provideObjectMapper();
    }
}
