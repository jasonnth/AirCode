package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideAirbnbEventLoggerFactory implements Factory<AirbnbEventLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAirbnbEventLoggerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AffiliateInfo> affiliateInfoProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final CoreModule module;
    private final Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider;

    public CoreModule_ProvideAirbnbEventLoggerFactory(CoreModule module2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AffiliateInfo> affiliateInfoProvider2, Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                if ($assertionsDisabled || affiliateInfoProvider2 != null) {
                    this.affiliateInfoProvider = affiliateInfoProvider2;
                    if ($assertionsDisabled || timeSkewAnalyticsProvider2 != null) {
                        this.timeSkewAnalyticsProvider = timeSkewAnalyticsProvider2;
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

    public AirbnbEventLogger get() {
        return (AirbnbEventLogger) Preconditions.checkNotNull(this.module.provideAirbnbEventLogger((AirbnbAccountManager) this.accountManagerProvider.get(), (AffiliateInfo) this.affiliateInfoProvider.get(), (TimeSkewAnalytics) this.timeSkewAnalyticsProvider.get(), (LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirbnbEventLogger> create(CoreModule module2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AffiliateInfo> affiliateInfoProvider2, Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new CoreModule_ProvideAirbnbEventLoggerFactory(module2, accountManagerProvider2, affiliateInfoProvider2, timeSkewAnalyticsProvider2, loggingContextFactoryProvider2);
    }

    public static AirbnbEventLogger proxyProvideAirbnbEventLogger(CoreModule instance, AirbnbAccountManager accountManager, AffiliateInfo affiliateInfo, TimeSkewAnalytics timeSkewAnalytics, LoggingContextFactory loggingContextFactory) {
        return instance.provideAirbnbEventLogger(accountManager, affiliateInfo, timeSkewAnalytics, loggingContextFactory);
    }
}
