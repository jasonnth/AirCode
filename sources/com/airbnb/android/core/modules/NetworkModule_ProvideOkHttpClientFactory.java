package com.airbnb.android.core.modules;

import com.airbnb.android.core.net.ApplicationInterceptorsProvider;
import com.airbnb.android.core.net.NetworkInterceptorsProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideOkHttpClientFactory.class.desiredAssertionStatus());
    private final Provider<ApplicationInterceptorsProvider> applicationInterceptorsProvider;
    private final Provider<Cache> cacheProvider;
    private final Provider<CookieJar> cookieJarProvider;
    private final NetworkModule module;
    private final Provider<NetworkInterceptorsProvider> networkInterceptorsProvider;

    public NetworkModule_ProvideOkHttpClientFactory(NetworkModule module2, Provider<Cache> cacheProvider2, Provider<NetworkInterceptorsProvider> networkInterceptorsProvider2, Provider<ApplicationInterceptorsProvider> applicationInterceptorsProvider2, Provider<CookieJar> cookieJarProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || cacheProvider2 != null) {
                this.cacheProvider = cacheProvider2;
                if ($assertionsDisabled || networkInterceptorsProvider2 != null) {
                    this.networkInterceptorsProvider = networkInterceptorsProvider2;
                    if ($assertionsDisabled || applicationInterceptorsProvider2 != null) {
                        this.applicationInterceptorsProvider = applicationInterceptorsProvider2;
                        if ($assertionsDisabled || cookieJarProvider2 != null) {
                            this.cookieJarProvider = cookieJarProvider2;
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

    public OkHttpClient get() {
        return (OkHttpClient) Preconditions.checkNotNull(this.module.provideOkHttpClient((Cache) this.cacheProvider.get(), (NetworkInterceptorsProvider) this.networkInterceptorsProvider.get(), (ApplicationInterceptorsProvider) this.applicationInterceptorsProvider.get(), (CookieJar) this.cookieJarProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<OkHttpClient> create(NetworkModule module2, Provider<Cache> cacheProvider2, Provider<NetworkInterceptorsProvider> networkInterceptorsProvider2, Provider<ApplicationInterceptorsProvider> applicationInterceptorsProvider2, Provider<CookieJar> cookieJarProvider2) {
        return new NetworkModule_ProvideOkHttpClientFactory(module2, cacheProvider2, networkInterceptorsProvider2, applicationInterceptorsProvider2, cookieJarProvider2);
    }

    public static OkHttpClient proxyProvideOkHttpClient(NetworkModule instance, Cache cache, NetworkInterceptorsProvider networkInterceptorsProvider2, ApplicationInterceptorsProvider applicationInterceptorsProvider2, CookieJar cookieJar) {
        return instance.provideOkHttpClient(cache, networkInterceptorsProvider2, applicationInterceptorsProvider2, cookieJar);
    }
}
