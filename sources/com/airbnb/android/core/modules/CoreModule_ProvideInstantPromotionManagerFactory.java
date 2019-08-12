package com.airbnb.android.core.modules;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.ErfAnalytics;
import com.airbnb.android.core.instant_promo.InstantPromotionManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideInstantPromotionManagerFactory implements Factory<InstantPromotionManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideInstantPromotionManagerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<ErfAnalytics> erfAnalyticsProvider;

    public CoreModule_ProvideInstantPromotionManagerFactory(Provider<ErfAnalytics> erfAnalyticsProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || erfAnalyticsProvider2 != null) {
            this.erfAnalyticsProvider = erfAnalyticsProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public InstantPromotionManager get() {
        return (InstantPromotionManager) Preconditions.checkNotNull(CoreModule.provideInstantPromotionManager((ErfAnalytics) this.erfAnalyticsProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<InstantPromotionManager> create(Provider<ErfAnalytics> erfAnalyticsProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new CoreModule_ProvideInstantPromotionManagerFactory(erfAnalyticsProvider2, accountManagerProvider2);
    }

    public static InstantPromotionManager proxyProvideInstantPromotionManager(ErfAnalytics erfAnalytics, AirbnbAccountManager accountManager) {
        return CoreModule.provideInstantPromotionManager(erfAnalytics, accountManager);
    }
}
