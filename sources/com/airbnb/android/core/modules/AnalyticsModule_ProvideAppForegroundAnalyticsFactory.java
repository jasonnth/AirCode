package com.airbnb.android.core.modules;

import com.airbnb.android.core.analytics.AppForegroundAnalytics;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideAppForegroundAnalyticsFactory implements Factory<AppForegroundAnalytics> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideAppForegroundAnalyticsFactory.class.desiredAssertionStatus());
    private final AnalyticsModule module;
    private final Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider;

    public AnalyticsModule_ProvideAppForegroundAnalyticsFactory(AnalyticsModule module2, Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || timeSkewAnalyticsProvider2 != null) {
                this.timeSkewAnalyticsProvider = timeSkewAnalyticsProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AppForegroundAnalytics get() {
        return (AppForegroundAnalytics) Preconditions.checkNotNull(this.module.provideAppForegroundAnalytics((TimeSkewAnalytics) this.timeSkewAnalyticsProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AppForegroundAnalytics> create(AnalyticsModule module2, Provider<TimeSkewAnalytics> timeSkewAnalyticsProvider2) {
        return new AnalyticsModule_ProvideAppForegroundAnalyticsFactory(module2, timeSkewAnalyticsProvider2);
    }

    public static AppForegroundAnalytics proxyProvideAppForegroundAnalytics(AnalyticsModule instance, TimeSkewAnalytics timeSkewAnalytics) {
        return instance.provideAppForegroundAnalytics(timeSkewAnalytics);
    }
}
