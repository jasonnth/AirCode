package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PaidAmenityJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvidePaidAmenityJitneyLoggerFactory implements Factory<PaidAmenityJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvidePaidAmenityJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvidePaidAmenityJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public PaidAmenityJitneyLogger get() {
        return (PaidAmenityJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.providePaidAmenityJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PaidAmenityJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvidePaidAmenityJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static PaidAmenityJitneyLogger proxyProvidePaidAmenityJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.providePaidAmenityJitneyLogger(loggingContextFactory);
    }
}
