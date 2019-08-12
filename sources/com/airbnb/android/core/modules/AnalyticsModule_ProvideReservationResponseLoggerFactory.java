package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideReservationResponseLoggerFactory implements Factory<ReservationResponseLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideReservationResponseLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideReservationResponseLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public ReservationResponseLogger get() {
        return (ReservationResponseLogger) Preconditions.checkNotNull(AnalyticsModule.provideReservationResponseLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ReservationResponseLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideReservationResponseLoggerFactory(loggingContextFactoryProvider2);
    }

    public static ReservationResponseLogger proxyProvideReservationResponseLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideReservationResponseLogger(loggingContextFactory);
    }
}
