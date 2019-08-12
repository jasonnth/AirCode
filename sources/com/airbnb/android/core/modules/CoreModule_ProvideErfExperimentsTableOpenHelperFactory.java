package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.erf.ErfExperimentsTableOpenHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideErfExperimentsTableOpenHelperFactory implements Factory<ErfExperimentsTableOpenHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideErfExperimentsTableOpenHelperFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final CoreModule module;
    private final Provider<ObjectMapper> objectMapperProvider;

    public CoreModule_ProvideErfExperimentsTableOpenHelperFactory(CoreModule module2, Provider<Context> contextProvider2, Provider<ObjectMapper> objectMapperProvider2) {
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

    public ErfExperimentsTableOpenHelper get() {
        return (ErfExperimentsTableOpenHelper) Preconditions.checkNotNull(this.module.provideErfExperimentsTableOpenHelper((Context) this.contextProvider.get(), (ObjectMapper) this.objectMapperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ErfExperimentsTableOpenHelper> create(CoreModule module2, Provider<Context> contextProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        return new CoreModule_ProvideErfExperimentsTableOpenHelperFactory(module2, contextProvider2, objectMapperProvider2);
    }

    public static ErfExperimentsTableOpenHelper proxyProvideErfExperimentsTableOpenHelper(CoreModule instance, Context context, ObjectMapper objectMapper) {
        return instance.provideErfExperimentsTableOpenHelper(context, objectMapper);
    }
}
