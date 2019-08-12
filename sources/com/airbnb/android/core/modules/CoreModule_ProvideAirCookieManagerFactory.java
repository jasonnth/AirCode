package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.utils.AirCookieManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideAirCookieManagerFactory implements Factory<AirCookieManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAirCookieManagerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideAirCookieManagerFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public AirCookieManager get() {
        return (AirCookieManager) Preconditions.checkNotNull(CoreModule.provideAirCookieManager((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirCookieManager> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideAirCookieManagerFactory(contextProvider2);
    }

    public static AirCookieManager proxyProvideAirCookieManager(Context context) {
        return CoreModule.provideAirCookieManager(context);
    }
}
