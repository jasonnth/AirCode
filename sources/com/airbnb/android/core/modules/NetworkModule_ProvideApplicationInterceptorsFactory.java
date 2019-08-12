package com.airbnb.android.core.modules;

import com.airbnb.airrequest.BaseUrl;
import com.airbnb.android.core.net.ApplicationInterceptorsProvider;
import com.airbnb.android.core.requests.base.ApiRequestQueryParamsInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideApplicationInterceptorsFactory implements Factory<ApplicationInterceptorsProvider> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideApplicationInterceptorsFactory.class.desiredAssertionStatus());
    private final Provider<BaseUrl> apiBaseUrlProvider;
    private final NetworkModule module;
    private final Provider<ApiRequestQueryParamsInterceptor> queryParamsInterceptorProvider;

    public NetworkModule_ProvideApplicationInterceptorsFactory(NetworkModule module2, Provider<BaseUrl> apiBaseUrlProvider2, Provider<ApiRequestQueryParamsInterceptor> queryParamsInterceptorProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || apiBaseUrlProvider2 != null) {
                this.apiBaseUrlProvider = apiBaseUrlProvider2;
                if ($assertionsDisabled || queryParamsInterceptorProvider2 != null) {
                    this.queryParamsInterceptorProvider = queryParamsInterceptorProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ApplicationInterceptorsProvider get() {
        return (ApplicationInterceptorsProvider) Preconditions.checkNotNull(this.module.provideApplicationInterceptors((BaseUrl) this.apiBaseUrlProvider.get(), (ApiRequestQueryParamsInterceptor) this.queryParamsInterceptorProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ApplicationInterceptorsProvider> create(NetworkModule module2, Provider<BaseUrl> apiBaseUrlProvider2, Provider<ApiRequestQueryParamsInterceptor> queryParamsInterceptorProvider2) {
        return new NetworkModule_ProvideApplicationInterceptorsFactory(module2, apiBaseUrlProvider2, queryParamsInterceptorProvider2);
    }

    public static ApplicationInterceptorsProvider proxyProvideApplicationInterceptors(NetworkModule instance, BaseUrl apiBaseUrl, ApiRequestQueryParamsInterceptor queryParamsInterceptor) {
        return instance.provideApplicationInterceptors(apiBaseUrl, queryParamsInterceptor);
    }
}
