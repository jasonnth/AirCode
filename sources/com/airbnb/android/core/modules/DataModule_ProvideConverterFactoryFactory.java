package com.airbnb.android.core.modules;

import com.airbnb.android.core.data.ConverterFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DataModule_ProvideConverterFactoryFactory implements Factory<ConverterFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DataModule_ProvideConverterFactoryFactory.class.desiredAssertionStatus());
    private final DataModule module;
    private final Provider<ObjectMapper> objectMapperProvider;

    public DataModule_ProvideConverterFactoryFactory(DataModule module2, Provider<ObjectMapper> objectMapperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || objectMapperProvider2 != null) {
                this.objectMapperProvider = objectMapperProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ConverterFactory get() {
        return (ConverterFactory) Preconditions.checkNotNull(this.module.provideConverterFactory(DoubleCheck.lazy(this.objectMapperProvider)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ConverterFactory> create(DataModule module2, Provider<ObjectMapper> objectMapperProvider2) {
        return new DataModule_ProvideConverterFactoryFactory(module2, objectMapperProvider2);
    }

    public static ConverterFactory proxyProvideConverterFactory(DataModule instance, Lazy<ObjectMapper> objectMapper) {
        return instance.provideConverterFactory(objectMapper);
    }
}
