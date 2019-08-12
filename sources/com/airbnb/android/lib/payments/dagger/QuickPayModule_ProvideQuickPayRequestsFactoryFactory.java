package com.airbnb.android.lib.payments.dagger;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class QuickPayModule_ProvideQuickPayRequestsFactoryFactory implements Factory<BillPriceQuoteRequestFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!QuickPayModule_ProvideQuickPayRequestsFactoryFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final QuickPayModule module;

    public QuickPayModule_ProvideQuickPayRequestsFactoryFactory(QuickPayModule module2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public BillPriceQuoteRequestFactory get() {
        return (BillPriceQuoteRequestFactory) Preconditions.checkNotNull(this.module.provideQuickPayRequestsFactory((AirbnbAccountManager) this.accountManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BillPriceQuoteRequestFactory> create(QuickPayModule module2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new QuickPayModule_ProvideQuickPayRequestsFactoryFactory(module2, accountManagerProvider2);
    }

    public static BillPriceQuoteRequestFactory proxyProvideQuickPayRequestsFactory(QuickPayModule instance, AirbnbAccountManager accountManager) {
        return instance.provideQuickPayRequestsFactory(accountManager);
    }
}
