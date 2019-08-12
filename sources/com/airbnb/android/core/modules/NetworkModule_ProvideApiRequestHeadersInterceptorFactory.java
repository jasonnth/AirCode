package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.net.AirbnbApiUrlMatcher;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideApiRequestHeadersInterceptorFactory implements Factory<ApiRequestHeadersInterceptor> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideApiRequestHeadersInterceptorFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AffiliateInfo> affiliateInfoProvider;
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<Context> contextProvider;
    private final NetworkModule module;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;
    private final Provider<AirbnbApiUrlMatcher> urlMatcherProvider;

    public NetworkModule_ProvideApiRequestHeadersInterceptorFactory(NetworkModule module2, Provider<Context> contextProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<AffiliateInfo> affiliateInfoProvider2, Provider<AirbnbApiUrlMatcher> urlMatcherProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || accountManagerProvider2 != null) {
                    this.accountManagerProvider = accountManagerProvider2;
                    if ($assertionsDisabled || airbnbApiProvider2 != null) {
                        this.airbnbApiProvider = airbnbApiProvider2;
                        if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                            this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
                            if ($assertionsDisabled || affiliateInfoProvider2 != null) {
                                this.affiliateInfoProvider = affiliateInfoProvider2;
                                if ($assertionsDisabled || urlMatcherProvider2 != null) {
                                    this.urlMatcherProvider = urlMatcherProvider2;
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
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ApiRequestHeadersInterceptor get() {
        return (ApiRequestHeadersInterceptor) Preconditions.checkNotNull(this.module.provideApiRequestHeadersInterceptor((Context) this.contextProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get(), (AirbnbApi) this.airbnbApiProvider.get(), (SharedPrefsHelper) this.sharedPrefsHelperProvider.get(), (AffiliateInfo) this.affiliateInfoProvider.get(), (AirbnbApiUrlMatcher) this.urlMatcherProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ApiRequestHeadersInterceptor> create(NetworkModule module2, Provider<Context> contextProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<AffiliateInfo> affiliateInfoProvider2, Provider<AirbnbApiUrlMatcher> urlMatcherProvider2) {
        return new NetworkModule_ProvideApiRequestHeadersInterceptorFactory(module2, contextProvider2, accountManagerProvider2, airbnbApiProvider2, sharedPrefsHelperProvider2, affiliateInfoProvider2, urlMatcherProvider2);
    }

    public static ApiRequestHeadersInterceptor proxyProvideApiRequestHeadersInterceptor(NetworkModule instance, Context context, AirbnbAccountManager accountManager, AirbnbApi airbnbApi, SharedPrefsHelper sharedPrefsHelper, AffiliateInfo affiliateInfo, AirbnbApiUrlMatcher urlMatcher) {
        return instance.provideApiRequestHeadersInterceptor(context, accountManager, airbnbApi, sharedPrefsHelper, affiliateInfo, urlMatcher);
    }
}
