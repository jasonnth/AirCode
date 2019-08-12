package com.airbnb.android.core.modules;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.HostReservationObjectJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideHostReservationObjectJitneyLoggerFactory implements Factory<HostReservationObjectJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideHostReservationObjectJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public AnalyticsModule_ProvideHostReservationObjectJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public HostReservationObjectJitneyLogger get() {
        return (HostReservationObjectJitneyLogger) Preconditions.checkNotNull(AnalyticsModule.provideHostReservationObjectJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<HostReservationObjectJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new AnalyticsModule_ProvideHostReservationObjectJitneyLoggerFactory(loggingContextFactoryProvider2);
    }

    public static HostReservationObjectJitneyLogger proxyProvideHostReservationObjectJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return AnalyticsModule.provideHostReservationObjectJitneyLogger(loggingContextFactory);
    }
}
