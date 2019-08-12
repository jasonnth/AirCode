package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.AvailabilityCalendarJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideAvailabilityCalendarJitneyLoggerFactory implements Factory<AvailabilityCalendarJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideAvailabilityCalendarJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideAvailabilityCalendarJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public AvailabilityCalendarJitneyLogger get() {
        return (AvailabilityCalendarJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideAvailabilityCalendarJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AvailabilityCalendarJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideAvailabilityCalendarJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static AvailabilityCalendarJitneyLogger proxyProvideAvailabilityCalendarJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideAvailabilityCalendarJitneyLogger(loggingContextFactory);
    }
}
