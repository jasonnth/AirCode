package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.DeviceInfo;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.utils.ClientSessionManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideLoggingContextFactoryFactory implements Factory<LoggingContextFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideLoggingContextFactoryFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AffiliateInfo> affiliateInfoProvider;
    private final Provider<AirbnbPreferences> airbnbPreferencesProvider;
    private final Provider<ClientSessionManager> clientSessionManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final CoreModule module;
    private final Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider;

    public CoreModule_ProvideLoggingContextFactoryFactory(CoreModule module2, Provider<Context> contextProvider2, Provider<DeviceInfo> deviceInfoProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AffiliateInfo> affiliateInfoProvider2, Provider<AirbnbPreferences> airbnbPreferencesProvider2, Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider2, Provider<ClientSessionManager> clientSessionManagerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || deviceInfoProvider2 != null) {
                    this.deviceInfoProvider = deviceInfoProvider2;
                    if ($assertionsDisabled || accountManagerProvider2 != null) {
                        this.accountManagerProvider = accountManagerProvider2;
                        if ($assertionsDisabled || affiliateInfoProvider2 != null) {
                            this.affiliateInfoProvider = affiliateInfoProvider2;
                            if ($assertionsDisabled || airbnbPreferencesProvider2 != null) {
                                this.airbnbPreferencesProvider = airbnbPreferencesProvider2;
                                if ($assertionsDisabled || timeSkewAnalyticsProvider2 != null) {
                                    this.timeSkewAnalyticsProvider = timeSkewAnalyticsProvider2;
                                    if ($assertionsDisabled || clientSessionManagerProvider2 != null) {
                                        this.clientSessionManagerProvider = clientSessionManagerProvider2;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public LoggingContextFactory get() {
        return (LoggingContextFactory) Preconditions.checkNotNull(this.module.provideLoggingContextFactory((Context) this.contextProvider.get(), (DeviceInfo) this.deviceInfoProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get(), (AffiliateInfo) this.affiliateInfoProvider.get(), (AirbnbPreferences) this.airbnbPreferencesProvider.get(), (TimeSkewAnalytics) this.timeSkewAnalyticsProvider.get(), (ClientSessionManager) this.clientSessionManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LoggingContextFactory> create(CoreModule module2, Provider<Context> contextProvider2, Provider<DeviceInfo> deviceInfoProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AffiliateInfo> affiliateInfoProvider2, Provider<AirbnbPreferences> airbnbPreferencesProvider2, Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider2, Provider<ClientSessionManager> clientSessionManagerProvider2) {
        return new CoreModule_ProvideLoggingContextFactoryFactory(module2, contextProvider2, deviceInfoProvider2, accountManagerProvider2, affiliateInfoProvider2, airbnbPreferencesProvider2, timeSkewAnalyticsProvider2, clientSessionManagerProvider2);
    }

    public static LoggingContextFactory proxyProvideLoggingContextFactory(CoreModule instance, Context context, DeviceInfo deviceInfo, AirbnbAccountManager accountManager, AffiliateInfo affiliateInfo, AirbnbPreferences airbnbPreferences, TimeSkewAnalytics timeSkewAnalytics, ClientSessionManager clientSessionManager) {
        return instance.provideLoggingContextFactory(context, deviceInfo, accountManager, affiliateInfo, airbnbPreferences, timeSkewAnalytics, clientSessionManager);
    }
}
