package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.fragments.AirDialogFragment_MembersInjector;
import com.airbnb.android.core.fragments.ZenDialog_MembersInjector;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ShakeFeedbackDialog_MembersInjector implements MembersInjector<ShakeFeedbackDialog> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ShakeFeedbackDialog_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<AirbnbApi> mAirbnbApiProvider;
    private final Provider<Bus> mBusProvider;
    private final Provider<CurrencyFormatter> mCurrencyHelperProvider;
    private final Provider<NavigationLogging> navigationAnalyticsProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;
    private final Provider<ResourceManager> resourceManagerProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public ShakeFeedbackDialog_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<AirbnbApi> mAirbnbApiProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
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
                                            if ($assertionsDisabled || preferencesProvider2 != null) {
                                                this.preferencesProvider = preferencesProvider2;
                                                if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                                                    this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ShakeFeedbackDialog> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DebugSettings> debugSettingsProvider2, Provider<NavigationLogging> navigationAnalyticsProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<AirbnbApi> mAirbnbApiProvider2, Provider<Bus> mBusProvider2, Provider<CurrencyFormatter> mCurrencyHelperProvider2, Provider<ResourceManager> resourceManagerProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new ShakeFeedbackDialog_MembersInjector(mAccountManagerProvider2, debugSettingsProvider2, navigationAnalyticsProvider2, airRequestInitializerProvider2, loggingContextFactoryProvider2, mAirbnbApiProvider2, mBusProvider2, mCurrencyHelperProvider2, resourceManagerProvider2, preferencesProvider2, sharedPrefsHelperProvider2);
    }

    public void injectMembers(ShakeFeedbackDialog instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        AirDialogFragment_MembersInjector.injectMAccountManager(instance, this.mAccountManagerProvider);
        AirDialogFragment_MembersInjector.injectDebugSettings(instance, this.debugSettingsProvider);
        AirDialogFragment_MembersInjector.injectNavigationAnalytics(instance, this.navigationAnalyticsProvider);
        AirDialogFragment_MembersInjector.injectAirRequestInitializer(instance, this.airRequestInitializerProvider);
        AirDialogFragment_MembersInjector.injectLoggingContextFactory(instance, this.loggingContextFactoryProvider);
        ZenDialog_MembersInjector.injectMAirbnbApi(instance, this.mAirbnbApiProvider);
        ZenDialog_MembersInjector.injectMAccountManager(instance, this.mAccountManagerProvider);
        ZenDialog_MembersInjector.injectMBus(instance, this.mBusProvider);
        ZenDialog_MembersInjector.injectMCurrencyHelper(instance, this.mCurrencyHelperProvider);
        ZenDialog_MembersInjector.injectResourceManager(instance, this.resourceManagerProvider);
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
        instance.sharedPrefsHelper = (SharedPrefsHelper) this.sharedPrefsHelperProvider.get();
    }

    public static void injectPreferences(ShakeFeedbackDialog instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }

    public static void injectSharedPrefsHelper(ShakeFeedbackDialog instance, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        instance.sharedPrefsHelper = (SharedPrefsHelper) sharedPrefsHelperProvider2.get();
    }
}
