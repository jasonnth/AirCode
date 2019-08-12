package com.airbnb.android.lib.receivers;

import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.utils.AppLaunchUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AppUpgradeReceiver_MembersInjector implements MembersInjector<AppUpgradeReceiver> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AppUpgradeReceiver_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider;
    private final Provider<AppLaunchUtils> mAppLaunchUtilsProvider;

    public AppUpgradeReceiver_MembersInjector(Provider<AppLaunchUtils> mAppLaunchUtilsProvider2, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2) {
        if ($assertionsDisabled || mAppLaunchUtilsProvider2 != null) {
            this.mAppLaunchUtilsProvider = mAppLaunchUtilsProvider2;
            if ($assertionsDisabled || appLaunchAnalyticsProvider2 != null) {
                this.appLaunchAnalyticsProvider = appLaunchAnalyticsProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AppUpgradeReceiver> create(Provider<AppLaunchUtils> mAppLaunchUtilsProvider2, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2) {
        return new AppUpgradeReceiver_MembersInjector(mAppLaunchUtilsProvider2, appLaunchAnalyticsProvider2);
    }

    public void injectMembers(AppUpgradeReceiver instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAppLaunchUtils = (AppLaunchUtils) this.mAppLaunchUtilsProvider.get();
        instance.appLaunchAnalytics = (AppLaunchAnalytics) this.appLaunchAnalyticsProvider.get();
    }

    public static void injectMAppLaunchUtils(AppUpgradeReceiver instance, Provider<AppLaunchUtils> mAppLaunchUtilsProvider2) {
        instance.mAppLaunchUtils = (AppLaunchUtils) mAppLaunchUtilsProvider2.get();
    }

    public static void injectAppLaunchAnalytics(AppUpgradeReceiver instance, Provider<AppLaunchAnalytics> appLaunchAnalyticsProvider2) {
        instance.appLaunchAnalytics = (AppLaunchAnalytics) appLaunchAnalyticsProvider2.get();
    }
}
