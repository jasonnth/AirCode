package com.airbnb.android.lib.activities;

import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.controllers.SplashScreenController;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.react.ReactDeepLinkRegistry;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EntryActivity_MembersInjector implements MembersInjector<EntryActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = (!EntryActivity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AppLaunchUtils> appLaunchUtilsProvider;
    private final Provider<DebugSettings> debugSettingsProvider;
    private final Provider<AppLaunchAnalytics> launchAnalyticsProvider;
    private final Provider<ReactDeepLinkRegistry> reactDeepLinkRegistryProvider;
    private final Provider<SplashScreenController> splashScreenControllerProvider;

    public EntryActivity_MembersInjector(Provider<SplashScreenController> splashScreenControllerProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AppLaunchUtils> appLaunchUtilsProvider2, Provider<ReactDeepLinkRegistry> reactDeepLinkRegistryProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<DebugSettings> debugSettingsProvider2) {
        if ($assertionsDisabled || splashScreenControllerProvider2 != null) {
            this.splashScreenControllerProvider = splashScreenControllerProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                if ($assertionsDisabled || appLaunchUtilsProvider2 != null) {
                    this.appLaunchUtilsProvider = appLaunchUtilsProvider2;
                    if ($assertionsDisabled || reactDeepLinkRegistryProvider2 != null) {
                        this.reactDeepLinkRegistryProvider = reactDeepLinkRegistryProvider2;
                        if ($assertionsDisabled || launchAnalyticsProvider2 != null) {
                            this.launchAnalyticsProvider = launchAnalyticsProvider2;
                            if ($assertionsDisabled || debugSettingsProvider2 != null) {
                                this.debugSettingsProvider = debugSettingsProvider2;
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

    public static MembersInjector<EntryActivity> create(Provider<SplashScreenController> splashScreenControllerProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<AppLaunchUtils> appLaunchUtilsProvider2, Provider<ReactDeepLinkRegistry> reactDeepLinkRegistryProvider2, Provider<AppLaunchAnalytics> launchAnalyticsProvider2, Provider<DebugSettings> debugSettingsProvider2) {
        return new EntryActivity_MembersInjector(splashScreenControllerProvider2, accountManagerProvider2, appLaunchUtilsProvider2, reactDeepLinkRegistryProvider2, launchAnalyticsProvider2, debugSettingsProvider2);
    }

    public void injectMembers(EntryActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.splashScreenController = (SplashScreenController) this.splashScreenControllerProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.appLaunchUtils = (AppLaunchUtils) this.appLaunchUtilsProvider.get();
        instance.reactDeepLinkRegistry = DoubleCheck.lazy(this.reactDeepLinkRegistryProvider);
        instance.launchAnalytics = (AppLaunchAnalytics) this.launchAnalyticsProvider.get();
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
    }

    public static void injectSplashScreenController(EntryActivity instance, Provider<SplashScreenController> splashScreenControllerProvider2) {
        instance.splashScreenController = (SplashScreenController) splashScreenControllerProvider2.get();
    }

    public static void injectAccountManager(EntryActivity instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectAppLaunchUtils(EntryActivity instance, Provider<AppLaunchUtils> appLaunchUtilsProvider2) {
        instance.appLaunchUtils = (AppLaunchUtils) appLaunchUtilsProvider2.get();
    }

    public static void injectReactDeepLinkRegistry(EntryActivity instance, Provider<ReactDeepLinkRegistry> reactDeepLinkRegistryProvider2) {
        instance.reactDeepLinkRegistry = DoubleCheck.lazy(reactDeepLinkRegistryProvider2);
    }

    public static void injectLaunchAnalytics(EntryActivity instance, Provider<AppLaunchAnalytics> launchAnalyticsProvider2) {
        instance.launchAnalytics = (AppLaunchAnalytics) launchAnalyticsProvider2.get();
    }

    public static void injectDebugSettings(EntryActivity instance, Provider<DebugSettings> debugSettingsProvider2) {
        instance.debugSettings = (DebugSettings) debugSettingsProvider2.get();
    }
}
