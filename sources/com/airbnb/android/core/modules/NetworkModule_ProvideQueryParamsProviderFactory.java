package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.airrequest.BaseUrl;
import com.airbnb.android.core.requests.base.ApiRequestQueryParamsInterceptor;
import com.airbnb.android.core.utils.CurrencyFormatter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideQueryParamsProviderFactory implements Factory<ApiRequestQueryParamsInterceptor> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideQueryParamsProviderFactory.class.desiredAssertionStatus());
    private final Provider<BaseUrl> apiBaseUrlProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CurrencyFormatter> currencyFormatterProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideQueryParamsProviderFactory(NetworkModule module2, Provider<Context> contextProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<BaseUrl> apiBaseUrlProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || currencyFormatterProvider2 != null) {
                    this.currencyFormatterProvider = currencyFormatterProvider2;
                    if ($assertionsDisabled || apiBaseUrlProvider2 != null) {
                        this.apiBaseUrlProvider = apiBaseUrlProvider2;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ApiRequestQueryParamsInterceptor get() {
        return (ApiRequestQueryParamsInterceptor) Preconditions.checkNotNull(this.module.provideQueryParamsProvider((Context) this.contextProvider.get(), (CurrencyFormatter) this.currencyFormatterProvider.get(), (BaseUrl) this.apiBaseUrlProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ApiRequestQueryParamsInterceptor> create(NetworkModule module2, Provider<Context> contextProvider2, Provider<CurrencyFormatter> currencyFormatterProvider2, Provider<BaseUrl> apiBaseUrlProvider2) {
        return new NetworkModule_ProvideQueryParamsProviderFactory(module2, contextProvider2, currencyFormatterProvider2, apiBaseUrlProvider2);
    }

    public static ApiRequestQueryParamsInterceptor proxyProvideQueryParamsProvider(NetworkModule instance, Context context, CurrencyFormatter currencyFormatter, BaseUrl apiBaseUrl) {
        return instance.provideQueryParamsProvider(context, currencyFormatter, apiBaseUrl);
    }
}
