package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideCalendarJitneyLoggerFactory implements Factory<CalendarJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideCalendarJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideCalendarJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public CalendarJitneyLogger get() {
        return (CalendarJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideCalendarJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CalendarJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideCalendarJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static CalendarJitneyLogger proxyProvideCalendarJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideCalendarJitneyLogger(loggingContextFactory);
    }
}
