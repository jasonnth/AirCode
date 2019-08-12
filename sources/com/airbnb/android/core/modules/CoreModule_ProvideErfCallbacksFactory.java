package com.airbnb.android.core.modules;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.ErfAnalytics;
import com.airbnb.android.core.erf.ErfCallbacks;
import com.airbnb.android.core.erf.ExperimentsProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideErfCallbacksFactory implements Factory<ErfCallbacks> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideErfCallbacksFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<ErfAnalytics> erfAnalyticsProvider;
    private final Provider<ExperimentsProvider> experimentsProvider;

    public CoreModule_ProvideErfCallbacksFactory(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<ErfAnalytics> erfAnalyticsProvider2, Provider<ExperimentsProvider> experimentsProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || erfAnalyticsProvider2 != null) {
                this.erfAnalyticsProvider = erfAnalyticsProvider2;
                if ($assertionsDisabled || experimentsProvider2 != null) {
                    this.experimentsProvider = experimentsProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ErfCallbacks get() {
        return (ErfCallbacks) Preconditions.checkNotNull(CoreModule.provideErfCallbacks((AirbnbAccountManager) this.accountManagerProvider.get(), (ErfAnalytics) this.erfAnalyticsProvider.get(), (ExperimentsProvider) this.experimentsProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ErfCallbacks> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<ErfAnalytics> erfAnalyticsProvider2, Provider<ExperimentsProvider> experimentsProvider2) {
        return new CoreModule_ProvideErfCallbacksFactory(accountManagerProvider2, erfAnalyticsProvider2, experimentsProvider2);
    }

    public static ErfCallbacks proxyProvideErfCallbacks(AirbnbAccountManager accountManager, ErfAnalytics erfAnalytics, ExperimentsProvider experimentsProvider2) {
        return CoreModule.provideErfCallbacks(accountManager, erfAnalytics, experimentsProvider2);
    }
}
