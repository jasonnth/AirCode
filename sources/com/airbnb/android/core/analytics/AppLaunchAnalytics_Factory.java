package com.airbnb.android.core.analytics;

import com.airbnb.android.core.AirbnbPreferences;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;

public final class AppLaunchAnalytics_Factory implements Factory<AppLaunchAnalytics> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AppLaunchAnalytics_Factory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> airbnbPreferencesProvider;
    private final MembersInjector<AppLaunchAnalytics> appLaunchAnalyticsMembersInjector;
    private final Provider<PerformanceLogger> performanceLoggerProvider;

    public AppLaunchAnalytics_Factory(MembersInjector<AppLaunchAnalytics> appLaunchAnalyticsMembersInjector2, Provider<PerformanceLogger> performanceLoggerProvider2, Provider<AirbnbPreferences> airbnbPreferencesProvider2) {
        if ($assertionsDisabled || appLaunchAnalyticsMembersInjector2 != null) {
            this.appLaunchAnalyticsMembersInjector = appLaunchAnalyticsMembersInjector2;
            if ($assertionsDisabled || performanceLoggerProvider2 != null) {
                this.performanceLoggerProvider = performanceLoggerProvider2;
                if ($assertionsDisabled || airbnbPreferencesProvider2 != null) {
                    this.airbnbPreferencesProvider = airbnbPreferencesProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AppLaunchAnalytics get() {
        return (AppLaunchAnalytics) MembersInjectors.injectMembers(this.appLaunchAnalyticsMembersInjector, new AppLaunchAnalytics((PerformanceLogger) this.performanceLoggerProvider.get(), (AirbnbPreferences) this.airbnbPreferencesProvider.get()));
    }

    public static Factory<AppLaunchAnalytics> create(MembersInjector<AppLaunchAnalytics> appLaunchAnalyticsMembersInjector2, Provider<PerformanceLogger> performanceLoggerProvider2, Provider<AirbnbPreferences> airbnbPreferencesProvider2) {
        return new AppLaunchAnalytics_Factory(appLaunchAnalyticsMembersInjector2, performanceLoggerProvider2, airbnbPreferencesProvider2);
    }
}
