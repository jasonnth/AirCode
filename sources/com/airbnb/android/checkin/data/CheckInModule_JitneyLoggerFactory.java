package com.airbnb.android.checkin.data;

import com.airbnb.android.checkin.GuestCheckInJitneyLogger;
import com.airbnb.android.core.LoggingContextFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CheckInModule_JitneyLoggerFactory implements Factory<GuestCheckInJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CheckInModule_JitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public CheckInModule_JitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public GuestCheckInJitneyLogger get() {
        return (GuestCheckInJitneyLogger) Preconditions.checkNotNull(CheckInModule.jitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GuestCheckInJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new CheckInModule_JitneyLoggerFactory(loggingContextFactoryProvider2);
    }
}
