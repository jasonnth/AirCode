package com.airbnb.android.managelisting;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.managelisting.analytics.CheckInJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ManageListingModule_CheckInJitneyLoggerFactory implements Factory<CheckInJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ManageListingModule_CheckInJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public ManageListingModule_CheckInJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public CheckInJitneyLogger get() {
        return (CheckInJitneyLogger) Preconditions.checkNotNull(ManageListingModule.checkInJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CheckInJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new ManageListingModule_CheckInJitneyLoggerFactory(loggingContextFactoryProvider2);
    }
}
