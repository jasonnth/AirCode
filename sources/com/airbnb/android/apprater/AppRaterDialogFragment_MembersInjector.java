package com.airbnb.android.apprater;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.fragments.AirDialogFragment_MembersInjector;
import com.airbnb.android.core.utils.DebugSettings;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AppRaterDialogFragment_MembersInjector implements MembersInjector<AppRaterDialogFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AppRaterDialogFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<AppRaterController> appRaterControllerProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;

    public AppRaterDialogFragment_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<AppRaterController> appRaterControllerProvider2) {
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
                            if ($assertionsDisabled || appRaterControllerProvider2 != null) {
                                this.appRaterControllerProvider = appRaterControllerProvider2;
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

    public static MembersInjector<AppRaterDialogFragment> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<AppRaterController> appRaterControllerProvider2) {
        return new AppRaterDialogFragment_MembersInjector(mAccountManagerProvider2, debugSettingsProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, appRaterControllerProvider2);
    }

    public void injectMembers(AppRaterDialogFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        AirDialogFragment_MembersInjector.injectMAccountManager(instance, this.mAccountManagerProvider);
        AirDialogFragment_MembersInjector.injectDebugSettings(instance, this.debugSettingsProvider);
        AirDialogFragment_MembersInjector.injectNavigationAnalytics(instance, this.navigationAnalyticsProvider);
        AirDialogFragment_MembersInjector.injectAirRequestInitializer(instance, this.airRequestInitializerProvider);
        AirDialogFragment_MembersInjector.injectLoggingContextFactory(instance, this.loggingContextFactoryProvider);
        instance.appRaterController = (AppRaterController) this.appRaterControllerProvider.get();
    }

    public static void injectAppRaterController(AppRaterDialogFragment instance, Provider<AppRaterController> appRaterControllerProvider2) {
        instance.appRaterController = (AppRaterController) appRaterControllerProvider2.get();
    }
}
