package com.airbnb.android.guestrecovery;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.guestrecovery.logging.GuestRecoveryLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class GuestRecoveryModule_ProvideGuestRecoveryLoggerFactory implements Factory<GuestRecoveryLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!GuestRecoveryModule_ProvideGuestRecoveryLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public GuestRecoveryModule_ProvideGuestRecoveryLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public GuestRecoveryLogger get() {
        return (GuestRecoveryLogger) Preconditions.checkNotNull(GuestRecoveryModule.provideGuestRecoveryLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GuestRecoveryLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new GuestRecoveryModule_ProvideGuestRecoveryLoggerFactory(loggingContextFactoryProvider2);
    }
}
