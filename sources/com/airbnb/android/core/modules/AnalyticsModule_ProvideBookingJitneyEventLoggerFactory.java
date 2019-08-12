package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideBookingJitneyEventLoggerFactory implements Factory<BookingJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideBookingJitneyEventLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideBookingJitneyEventLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public BookingJitneyLogger get() {
        return (BookingJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideBookingJitneyEventLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BookingJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideBookingJitneyEventLoggerFactory(loggingContextFactoryProvider2);
    }

    public static BookingJitneyLogger proxyProvideBookingJitneyEventLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideBookingJitneyEventLogger(loggingContextFactory);
    }
}
