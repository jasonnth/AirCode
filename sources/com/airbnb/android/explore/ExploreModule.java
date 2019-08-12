package com.airbnb.android.explore;

import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.explore.ChildScope;
import com.airbnb.android.explore.controllers.ExploreDataRepository;
import com.airbnb.android.explore.controllers.ExploreDataRepositoryImpl;
import com.airbnb.android.explore.controllers.ExplorePerformanceAnalytics;

public class ExploreModule {
    @ChildScope
    public ExploreDataRepository provideExploreDataRepository() {
        return new ExploreDataRepositoryImpl();
    }

    @ChildScope
    public ExplorePerformanceAnalytics provideExplorePerformanceAnalytics(PerformanceLogger perfLogger) {
        return new ExplorePerformanceAnalytics(perfLogger);
    }
}
