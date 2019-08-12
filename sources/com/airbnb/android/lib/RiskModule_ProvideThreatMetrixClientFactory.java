package com.airbnb.android.lib;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.security.ThreatMetrixClient;
import com.threatmetrix.TrustDefender.Config;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class RiskModule_ProvideThreatMetrixClientFactory implements Factory<ThreatMetrixClient> {
    static final /* synthetic */ boolean $assertionsDisabled = (!RiskModule_ProvideThreatMetrixClientFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<Config> configProvider;
    private final RiskModule module;

    public RiskModule_ProvideThreatMetrixClientFactory(RiskModule module2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Config> configProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                if ($assertionsDisabled || configProvider2 != null) {
                    this.configProvider = configProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ThreatMetrixClient get() {
        return (ThreatMetrixClient) Preconditions.checkNotNull(this.module.provideThreatMetrixClient((AirbnbAccountManager) this.accountManagerProvider.get(), (Config) this.configProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ThreatMetrixClient> create(RiskModule module2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Config> configProvider2) {
        return new RiskModule_ProvideThreatMetrixClientFactory(module2, accountManagerProvider2, configProvider2);
    }

    public static ThreatMetrixClient proxyProvideThreatMetrixClient(RiskModule instance, AirbnbAccountManager accountManager, Config config) {
        return instance.provideThreatMetrixClient(accountManager, config);
    }
}
