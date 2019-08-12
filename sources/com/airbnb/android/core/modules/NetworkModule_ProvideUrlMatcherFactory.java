package com.airbnb.android.core.modules;

import com.airbnb.airrequest.BaseUrl;
import com.airbnb.android.core.net.AirbnbApiUrlMatcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideUrlMatcherFactory implements Factory<AirbnbApiUrlMatcher> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideUrlMatcherFactory.class.desiredAssertionStatus());
    private final Provider<BaseUrl> baseUrlProvider;

    public NetworkModule_ProvideUrlMatcherFactory(Provider<BaseUrl> baseUrlProvider2) {
        if ($assertionsDisabled || baseUrlProvider2 != null) {
            this.baseUrlProvider = baseUrlProvider2;
            return;
        }
        throw new AssertionError();
    }

    public AirbnbApiUrlMatcher get() {
        return (AirbnbApiUrlMatcher) Preconditions.checkNotNull(NetworkModule.provideUrlMatcher((BaseUrl) this.baseUrlProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirbnbApiUrlMatcher> create(Provider<BaseUrl> baseUrlProvider2) {
        return new NetworkModule_ProvideUrlMatcherFactory(baseUrlProvider2);
    }

    public static AirbnbApiUrlMatcher proxyProvideUrlMatcher(BaseUrl baseUrl) {
        return NetworkModule.provideUrlMatcher(baseUrl);
    }
}
