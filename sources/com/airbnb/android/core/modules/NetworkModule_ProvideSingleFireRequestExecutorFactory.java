package com.airbnb.android.core.modules;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.SingleFireRequestExecutor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideSingleFireRequestExecutorFactory implements Factory<SingleFireRequestExecutor> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideSingleFireRequestExecutorFactory.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> initializerProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideSingleFireRequestExecutorFactory(NetworkModule module2, Provider<AirRequestInitializer> initializerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || initializerProvider2 != null) {
                this.initializerProvider = initializerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public SingleFireRequestExecutor get() {
        return (SingleFireRequestExecutor) Preconditions.checkNotNull(this.module.provideSingleFireRequestExecutor((AirRequestInitializer) this.initializerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SingleFireRequestExecutor> create(NetworkModule module2, Provider<AirRequestInitializer> initializerProvider2) {
        return new NetworkModule_ProvideSingleFireRequestExecutorFactory(module2, initializerProvider2);
    }

    public static SingleFireRequestExecutor proxyProvideSingleFireRequestExecutor(NetworkModule instance, AirRequestInitializer initializer) {
        return instance.provideSingleFireRequestExecutor(initializer);
    }
}
