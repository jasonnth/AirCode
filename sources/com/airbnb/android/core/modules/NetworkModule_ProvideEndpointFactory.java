package com.airbnb.android.core.modules;

import com.airbnb.airrequest.BaseUrl;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class NetworkModule_ProvideEndpointFactory implements Factory<BaseUrl> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideEndpointFactory.class.desiredAssertionStatus());
    private final NetworkModule module;

    public NetworkModule_ProvideEndpointFactory(NetworkModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public BaseUrl get() {
        return (BaseUrl) Preconditions.checkNotNull(this.module.provideEndpoint(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BaseUrl> create(NetworkModule module2) {
        return new NetworkModule_ProvideEndpointFactory(module2);
    }

    public static BaseUrl proxyProvideEndpoint(NetworkModule instance) {
        return instance.provideEndpoint();
    }
}
