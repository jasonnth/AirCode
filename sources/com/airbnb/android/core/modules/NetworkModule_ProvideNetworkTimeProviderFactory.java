package com.airbnb.android.core.modules;

import com.airbnb.android.core.services.NetworkTimeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class NetworkModule_ProvideNetworkTimeProviderFactory implements Factory<NetworkTimeProvider> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideNetworkTimeProviderFactory.class.desiredAssertionStatus());
    private final NetworkModule module;

    public NetworkModule_ProvideNetworkTimeProviderFactory(NetworkModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public NetworkTimeProvider get() {
        return (NetworkTimeProvider) Preconditions.checkNotNull(this.module.provideNetworkTimeProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<NetworkTimeProvider> create(NetworkModule module2) {
        return new NetworkModule_ProvideNetworkTimeProviderFactory(module2);
    }
}
