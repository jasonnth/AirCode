package com.airbnb.android.core.fragments;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.DebugSettings;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AirDialogFragment_MembersInjector implements MembersInjector<AirDialogFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AirDialogFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;

    public AirDialogFragment_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
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

    public static MembersInjector<AirDialogFragment> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AirDialogFragment_MembersInjector(mAccountManagerProvider2, debugSettingsProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2);
    }

    public void injectMembers(AirDialogFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
        instance.navigationAnalytics = (NavigationLogging) this.navigationAnalyticsProvider.get();
        instance.airRequestInitializer = (AirRequestInitializer) this.airRequestInitializerProvider.get();
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
    }

    public static void injectMAccountManager(AirDialogFragment instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectDebugSettings(AirDialogFragment instance, Provider<DebugSettings> debugSettingsProvider2) {
        instance.debugSettings = (DebugSettings) debugSettingsProvider2.get();
    }

    public static void injectNavigationAnalytics(AirDialogFragment instance, Provider<NavigationLogging> navigationAnalyticsProvider2) {
        instance.navigationAnalytics = (NavigationLogging) navigationAnalyticsProvider2.get();
    }

    public static void injectAirRequestInitializer(AirDialogFragment instance, Provider<AirRequestInitializer> airRequestInitializerProvider2) {
        instance.airRequestInitializer = (AirRequestInitializer) airRequestInitializerProvider2.get();
    }

    public static void injectLoggingContextFactory(AirDialogFragment instance, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        instance.loggingContextFactory = (LoggingContextFactory) loggingContextFactoryProvider2.get();
    }
}
