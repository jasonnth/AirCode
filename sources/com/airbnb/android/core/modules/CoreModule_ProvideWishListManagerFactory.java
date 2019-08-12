package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.wishlists.WishListManager;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideWishListManagerFactory implements Factory<WishListManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideWishListManagerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<Bus> busProvider;
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideWishListManagerFactory(Provider<Context> contextProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Bus> busProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                if ($assertionsDisabled || busProvider2 != null) {
                    this.busProvider = busProvider2;
                    if ($assertionsDisabled || airRequestInitializerProvider2 != null) {
                        this.airRequestInitializerProvider = airRequestInitializerProvider2;
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

    public WishListManager get() {
        return (WishListManager) Preconditions.checkNotNull(CoreModule.provideWishListManager((Context) this.contextProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get(), (Bus) this.busProvider.get(), (AirRequestInitializer) this.airRequestInitializerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WishListManager> create(Provider<Context> contextProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Bus> busProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2) {
        return new CoreModule_ProvideWishListManagerFactory(contextProvider2, accountManagerProvider2, busProvider2, airRequestInitializerProvider2);
    }

    public static WishListManager proxyProvideWishListManager(Context context, AirbnbAccountManager accountManager, Bus bus, AirRequestInitializer airRequestInitializer) {
        return CoreModule.provideWishListManager(context, accountManager, bus, airRequestInitializer);
    }
}
