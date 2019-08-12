package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.ReservationCancellationLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideReservationCancellationLoggerFactory implements Factory<ReservationCancellationLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideReservationCancellationLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideReservationCancellationLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public ReservationCancellationLogger get() {
        return (ReservationCancellationLogger) Preconditions.checkNotNull(AnalyticsModule.provideReservationCancellationLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ReservationCancellationLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideReservationCancellationLoggerFactory(loggingContextFactoryProvider2);
    }

    public static ReservationCancellationLogger proxyProvideReservationCancellationLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideReservationCancellationLogger(loggingContextFactory);
    }
}
