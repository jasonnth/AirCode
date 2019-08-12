package com.airbnb.android.explore;

import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.explore.controllers.ExplorePerformanceAnalytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExploreModule_ProvideExplorePerformanceAnalyticsFactory implements Factory<ExplorePerformanceAnalytics> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ExploreModule_ProvideExplorePerformanceAnalyticsFactory.class.desiredAssertionStatus());
    private final ExploreModule module;
    private final Provider<PerformanceLogger> perfLoggerProvider;

    public ExploreModule_ProvideExplorePerformanceAnalyticsFactory(ExploreModule module2, Provider<PerformanceLogger> perfLoggerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || perfLoggerProvider2 != null) {
                this.perfLoggerProvider = perfLoggerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ExplorePerformanceAnalytics get() {
        return (ExplorePerformanceAnalytics) Preconditions.checkNotNull(this.module.provideExplorePerformanceAnalytics((PerformanceLogger) this.perfLoggerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ExplorePerformanceAnalytics> create(ExploreModule module2, Provider<PerformanceLogger> perfLoggerProvider2) {
        return new ExploreModule_ProvideExplorePerformanceAnalyticsFactory(module2, perfLoggerProvider2);
    }
}
