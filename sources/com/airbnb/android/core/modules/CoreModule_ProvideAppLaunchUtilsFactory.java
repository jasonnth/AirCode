package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.persist.DomainStore;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.google.android.gms.analytics.Tracker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideAppLaunchUtilsFactory implements Factory<AppLaunchUtils> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAppLaunchUtilsFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AffiliateInfo> affiliateInfoProvider;
    private final Provider<DomainStore> domainStoreProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;
    private final Provider<Tracker> trackerProvider;

    public CoreModule_ProvideAppLaunchUtilsFactory(Provider<AirbnbPreferences> preferencesProvider2, Provider<AffiliateInfo> affiliateInfoProvider2, Provider<DomainStore> domainStoreProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Tracker> trackerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            if ($assertionsDisabled || affiliateInfoProvider2 != null) {
                this.affiliateInfoProvider = affiliateInfoProvider2;
                if ($assertionsDisabled || domainStoreProvider2 != null) {
                    this.domainStoreProvider = domainStoreProvider2;
                    if ($assertionsDisabled || accountManagerProvider2 != null) {
                        this.accountManagerProvider = accountManagerProvider2;
                        if ($assertionsDisabled || trackerProvider2 != null) {
                            this.trackerProvider = trackerProvider2;
                            if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                                this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
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
        throw new AssertionError();
    }

    public AppLaunchUtils get() {
        return (AppLaunchUtils) Preconditions.checkNotNull(CoreModule.provideAppLaunchUtils((AirbnbPreferences) this.preferencesProvider.get(), (AffiliateInfo) this.affiliateInfoProvider.get(), (DomainStore) this.domainStoreProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get(), (Tracker) this.trackerProvider.get(), (LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AppLaunchUtils> create(Provider<AirbnbPreferences> preferencesProvider2, Provider<AffiliateInfo> affiliateInfoProvider2, Provider<DomainStore> domainStoreProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<Tracker> trackerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new CoreModule_ProvideAppLaunchUtilsFactory(preferencesProvider2, affiliateInfoProvider2, domainStoreProvider2, accountManagerProvider2, trackerProvider2, loggingContextFactoryProvider2);
    }

    public static AppLaunchUtils proxyProvideAppLaunchUtils(AirbnbPreferences preferences, AffiliateInfo affiliateInfo, DomainStore domainStore, AirbnbAccountManager accountManager, Tracker tracker, LoggingContextFactory loggingContextFactory) {
        return CoreModule.provideAppLaunchUtils(preferences, affiliateInfo, domainStore, accountManager, tracker, loggingContextFactory);
    }
}
