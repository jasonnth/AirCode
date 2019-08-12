package com.airbnb.android.core.modules;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.host.ListingPromoController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvidePromoControllerFactory implements Factory<ListingPromoController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvidePromoControllerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;

    public CoreModule_ProvidePromoControllerFactory(Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public ListingPromoController get() {
        return (ListingPromoController) Preconditions.checkNotNull(CoreModule.providePromoController((AirbnbAccountManager) this.accountManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ListingPromoController> create(Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new CoreModule_ProvidePromoControllerFactory(accountManagerProvider2);
    }

    public static ListingPromoController proxyProvidePromoController(AirbnbAccountManager accountManager) {
        return CoreModule.providePromoController(accountManager);
    }
}
