package com.airbnb.android.core.modules;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.net.NetworkInterceptorsProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideNetworkInterceptorsFactory implements Factory<NetworkInterceptorsProvider> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideNetworkInterceptorsFactory.class.desiredAssertionStatus());
    private final NetworkModule module;
    private final Provider<ApiRequestHeadersInterceptor> requestHeadersInterceptorProvider;

    public NetworkModule_ProvideNetworkInterceptorsFactory(NetworkModule module2, Provider<ApiRequestHeadersInterceptor> requestHeadersInterceptorProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || requestHeadersInterceptorProvider2 != null) {
                this.requestHeadersInterceptorProvider = requestHeadersInterceptorProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public NetworkInterceptorsProvider get() {
        return (NetworkInterceptorsProvider) Preconditions.checkNotNull(this.module.provideNetworkInterceptors((ApiRequestHeadersInterceptor) this.requestHeadersInterceptorProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<NetworkInterceptorsProvider> create(NetworkModule module2, Provider<ApiRequestHeadersInterceptor> requestHeadersInterceptorProvider2) {
        return new NetworkModule_ProvideNetworkInterceptorsFactory(module2, requestHeadersInterceptorProvider2);
    }

    public static NetworkInterceptorsProvider proxyProvideNetworkInterceptors(NetworkModule instance, ApiRequestHeadersInterceptor requestHeadersInterceptor) {
        return instance.provideNetworkInterceptors(requestHeadersInterceptor);
    }
}
