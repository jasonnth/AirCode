package com.airbnb.android.lib.payments.dagger;

import android.content.Context;
import com.airbnb.android.lib.payments.digitalriver.DigitalRiverApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class QuickPayModule_ProvideDigitalRiverApiFactory implements Factory<DigitalRiverApi> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvideDigitalRiverApiFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final QuickPayModule module;
    private final Provider<ObjectMapper> objectMapperProvider;

    public QuickPayModule_ProvideDigitalRiverApiFactory(QuickPayModule module2, Provider<Context> contextProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || objectMapperProvider2 != null) {
                    this.objectMapperProvider = objectMapperProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public DigitalRiverApi get() {
        return (DigitalRiverApi) Preconditions.checkNotNull(this.module.provideDigitalRiverApi((Context) this.contextProvider.get(), (ObjectMapper) this.objectMapperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DigitalRiverApi> create(QuickPayModule module2, Provider<Context> contextProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        return new QuickPayModule_ProvideDigitalRiverApiFactory(module2, contextProvider2, objectMapperProvider2);
    }

    public static DigitalRiverApi proxyProvideDigitalRiverApi(QuickPayModule instance, Context context, ObjectMapper objectMapper) {
        return instance.provideDigitalRiverApi(context, objectMapper);
    }
}
