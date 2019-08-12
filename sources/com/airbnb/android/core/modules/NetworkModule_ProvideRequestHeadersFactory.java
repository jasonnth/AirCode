package com.airbnb.android.core.modules;

import com.airbnb.android.core.requests.base.AirRequestHeadersInterceptor;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideRequestHeadersFactory implements Factory<AirRequestHeadersInterceptor> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideRequestHeadersFactory.class.desiredAssertionStatus());
    private final NetworkModule module;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public NetworkModule_ProvideRequestHeadersFactory(NetworkModule module2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AirRequestHeadersInterceptor get() {
        return (AirRequestHeadersInterceptor) Preconditions.checkNotNull(this.module.provideRequestHeaders((SharedPrefsHelper) this.sharedPrefsHelperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirRequestHeadersInterceptor> create(NetworkModule module2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new NetworkModule_ProvideRequestHeadersFactory(module2, sharedPrefsHelperProvider2);
    }

    public static AirRequestHeadersInterceptor proxyProvideRequestHeaders(NetworkModule instance, SharedPrefsHelper sharedPrefsHelper) {
        return instance.provideRequestHeaders(sharedPrefsHelper);
    }
}
