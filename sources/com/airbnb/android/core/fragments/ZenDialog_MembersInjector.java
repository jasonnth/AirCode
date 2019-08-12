package com.airbnb.android.core.fragments;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ZenDialog_MembersInjector implements MembersInjector<ZenDialog> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ZenDialog_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<AirbnbApi> mAirbnbApiProvider;
    private final Provider<Bus> mBusProvider;
    private final Provider<CurrencyFormatter> mCurrencyHelperProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;
    private final Provider<ResourceManager> resourceManagerProvider;

    public ZenDialog_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<AirbnbApi> mAirbnbApiProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<ResourceManager> resourceManagerProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            if ($assertionsDisabled || debugSettingsProvider2 != null) {
                this.debugSettingsProvider = debugSettingsProvider2;
                if ($assertionsDisabled || navigationAnalyticsProvider2 != null) {
                    this.navigationAnalyticsProvider = navigationAnalyticsProvider2;
                    if ($assertionsDisabled || airRequestInitializerProvider2 != null) {
                        this.airRequestInitializerProvider = airRequestInitializerProvider2;
                        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                            if ($assertionsDisabled || mAirbnbApiProvider2 != null) {
                                this.mAirbnbApiProvider = mAirbnbApiProvider2;
                                if ($assertionsDisabled || mBusProvider2 != null) {
                                    this.mBusProvider = mBusProvider2;
                                    if ($assertionsDisabled || mCurrencyHelperProvider2 != null) {
                                        this.mCurrencyHelperProvider = mCurrencyHelperProvider2;
                                        if ($assertionsDisabled || resourceManagerProvider2 != null) {
                                            this.resourceManagerProvider = resourceManagerProvider2;
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
        throw new AssertionError();
    }

    public static MembersInjector<ZenDialog> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<AirbnbApi> mAirbnbApiProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<ResourceManager> resourceManagerProvider2) {
        return new ZenDialog_MembersInjector(mAccountManagerProvider2, debugSettingsProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, mAirbnbApiProvider2, mBusProvider2, mCurrencyHelperProvider2, resourceManagerProvider2);
    }

    public void injectMembers(ZenDialog instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
        instance.navigationAnalytics = (NavigationLogging) this.navigationAnalyticsProvider.get();
        instance.airRequestInitializer = (AirRequestInitializer) this.airRequestInitializerProvider.get();
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
        instance.mAirbnbApi = (AirbnbApi) this.mAirbnbApiProvider.get();
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.mBus = (Bus) this.mBusProvider.get();
        instance.mCurrencyHelper = (CurrencyFormatter) this.mCurrencyHelperProvider.get();
        instance.resourceManager = (ResourceManager) this.resourceManagerProvider.get();
    }

    public static void injectMAirbnbApi(ZenDialog instance, Provider<AirbnbApi> mAirbnbApiProvider2) {
        instance.mAirbnbApi = (AirbnbApi) mAirbnbApiProvider2.get();
    }

    public static void injectMAccountManager(ZenDialog instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectMBus(ZenDialog instance, Provider<Bus> mBusProvider2) {
        instance.mBus = (Bus) mBusProvider2.get();
    }

    public static void injectMCurrencyHelper(ZenDialog instance, Provider<CurrencyFormatter> mCurrencyHelperProvider2) {
        instance.mCurrencyHelper = (CurrencyFormatter) mCurrencyHelperProvider2.get();
    }

    public static void injectResourceManager(ZenDialog instance, Provider<ResourceManager> resourceManagerProvider2) {
        instance.resourceManager = (ResourceManager) resourceManagerProvider2.get();
    }
}
