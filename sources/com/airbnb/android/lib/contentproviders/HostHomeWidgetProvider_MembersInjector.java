package com.airbnb.android.lib.contentproviders;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HostHomeWidgetProvider_MembersInjector implements MembersInjector<HostHomeWidgetProvider> {
    static final /* synthetic */ boolean $assertionsDisabled = (!HostHomeWidgetProvider_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;

    public HostHomeWidgetProvider_MembersInjector(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2) {
        if ($assertionsDisabled || mAccountManagerProvider2 != null) {
            this.mAccountManagerProvider = mAccountManagerProvider2;
            if ($assertionsDisabled || airbnbApiProvider2 != null) {
                this.airbnbApiProvider = airbnbApiProvider2;
                if ($assertionsDisabled || appLaunchAnalyticsProvider2 != null) {
                    this.appLaunchAnalyticsProvider = appLaunchAnalyticsProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<HostHomeWidgetProvider> create(Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2) {
        return new HostHomeWidgetProvider_MembersInjector(mAccountManagerProvider2, airbnbApiProvider2, appLaunchAnalyticsProvider2);
    }

    public void injectMembers(HostHomeWidgetProvider instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.airbnbApi = (AirbnbApi) this.airbnbApiProvider.get();
        instance.appLaunchAnalytics = (AppLaunchAnalytics) this.appLaunchAnalyticsProvider.get();
    }

    public static void injectMAccountManager(HostHomeWidgetProvider instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectAirbnbApi(HostHomeWidgetProvider instance, Provider<AirbnbApi> airbnbApiProvider2) {
        instance.airbnbApi = (AirbnbApi) airbnbApiProvider2.get();
    }

    public static void injectAppLaunchAnalytics(HostHomeWidgetProvider instance, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2) {
        instance.appLaunchAnalytics = (AppLaunchAnalytics) appLaunchAnalyticsProvider2.get();
    }
}
