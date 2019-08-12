package com.airbnb.android.core.modules;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.PerformanceLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideAppLaunchAnalyticsFactory implements Factory<AppLaunchAnalytics> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAppLaunchAnalyticsFactory.class.desiredAssertionStatus());
    private final Provider<PerformanceLogger> performanceLoggerProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public CoreModule_ProvideAppLaunchAnalyticsFactory(Provider<PerformanceLogger> performanceLoggerProvider2, Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || performanceLoggerProvider2 != null) {
            this.performanceLoggerProvider = performanceLoggerProvider2;
            if ($assertionsDisabled || preferencesProvider2 != null) {
                this.preferencesProvider = preferencesProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AppLaunchAnalytics get() {
        return (AppLaunchAnalytics) Preconditions.checkNotNull(CoreModule.provideAppLaunchAnalytics((PerformanceLogger) this.performanceLoggerProvider.get(), (AirbnbPreferences) this.preferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AppLaunchAnalytics> create(Provider<PerformanceLogger> performanceLoggerProvider2, Provider<AirbnbPreferences> preferencesProvider2) {
        return new CoreModule_ProvideAppLaunchAnalyticsFactory(performanceLoggerProvider2, preferencesProvider2);
    }

    public static AppLaunchAnalytics proxyProvideAppLaunchAnalytics(PerformanceLogger performanceLogger, AirbnbPreferences preferences) {
        return CoreModule.provideAppLaunchAnalytics(performanceLogger, preferences);
    }
}
