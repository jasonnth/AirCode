package com.airbnb.android.core.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import retrofit2.CallAdapter;

public final class NetworkModule_ProvideCallAdapterFactoryFactory implements Factory<CallAdapter.Factory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideCallAdapterFactoryFactory.class.desiredAssertionStatus());
    private final NetworkModule module;

    public NetworkModule_ProvideCallAdapterFactoryFactory(NetworkModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public CallAdapter.Factory get() {
        return (CallAdapter.Factory) Preconditions.checkNotNull(this.module.provideCallAdapterFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CallAdapter.Factory> create(NetworkModule module2) {
        return new NetworkModule_ProvideCallAdapterFactoryFactory(module2);
    }

    public static CallAdapter.Factory proxyProvideCallAdapterFactory(NetworkModule instance) {
        return instance.provideCallAdapterFactory();
    }
}
