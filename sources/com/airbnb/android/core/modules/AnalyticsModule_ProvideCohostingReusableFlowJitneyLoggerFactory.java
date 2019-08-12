package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.CohostingReusableFlowJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideCohostingReusableFlowJitneyLoggerFactory implements Factory<CohostingReusableFlowJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideCohostingReusableFlowJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideCohostingReusableFlowJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public CohostingReusableFlowJitneyLogger get() {
        return (CohostingReusableFlowJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideCohostingReusableFlowJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CohostingReusableFlowJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideCohostingReusableFlowJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static CohostingReusableFlowJitneyLogger proxyProvideCohostingReusableFlowJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideCohostingReusableFlowJitneyLogger(loggingContextFactory);
    }
}
