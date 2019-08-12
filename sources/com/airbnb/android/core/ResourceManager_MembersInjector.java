package com.airbnb.android.core;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.ErfAnalytics;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ResourceManager_MembersInjector implements MembersInjector<ResourceManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ResourceManager_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<ErfAnalytics> erfAnalyticsProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public ResourceManager_MembersInjector(Provider<AirbnbPreferences> preferencesProvider2, Provider<ErfAnalytics> erfAnalyticsProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
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
        throw new AssertionError();
    }

    public static MembersInjector<ResourceManager> create(Provider<AirbnbPreferences> preferencesProvider2, Provider<ErfAnalytics> erfAnalyticsProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new ResourceManager_MembersInjector(preferencesProvider2, erfAnalyticsProvider2, accountManagerProvider2);
    }

    public void injectMembers(ResourceManager instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
        instance.erfAnalytics = (ErfAnalytics) this.erfAnalyticsProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectPreferences(ResourceManager instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }

    public static void injectErfAnalytics(ResourceManager instance, Provider<ErfAnalytics> erfAnalyticsProvider2) {
        instance.erfAnalytics = (ErfAnalytics) erfAnalyticsProvider2.get();
    }

    public static void injectAccountManager(ResourceManager instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
