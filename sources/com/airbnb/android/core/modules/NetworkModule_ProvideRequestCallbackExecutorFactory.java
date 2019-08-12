package com.airbnb.android.core.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.Executor;

public final class NetworkModule_ProvideRequestCallbackExecutorFactory implements Factory<Executor> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideRequestCallbackExecutorFactory.class.desiredAssertionStatus());
    private final NetworkModule module;

    public NetworkModule_ProvideRequestCallbackExecutorFactory(NetworkModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public Executor get() {
        return (Executor) Preconditions.checkNotNull(this.module.provideRequestCallbackExecutor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Executor> create(NetworkModule module2) {
        return new NetworkModule_ProvideRequestCallbackExecutorFactory(module2);
    }

    public static Executor proxyProvideRequestCallbackExecutor(NetworkModule instance) {
        return instance.provideRequestCallbackExecutor();
    }
}
