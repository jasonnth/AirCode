package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideCohostingManagementJitneyLoggerFactory implements Factory<CohostingManagementJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideCohostingManagementJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideCohostingManagementJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public CohostingManagementJitneyLogger get() {
        return (CohostingManagementJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideCohostingManagementJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CohostingManagementJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideCohostingManagementJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static CohostingManagementJitneyLogger proxyProvideCohostingManagementJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideCohostingManagementJitneyLogger(loggingContextFactory);
    }
}
