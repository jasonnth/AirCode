package com.airbnb.android.guestrecovery;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.explore.ChildScope;
import com.airbnb.android.guestrecovery.logging.GuestRecoveryLogger;

public class GuestRecoveryModule {
    @ChildScope
    public static GuestRecoveryLogger provideGuestRecoveryLogger(LoggingContextFactory loggingContextFactory) {
        return new GuestRecoveryLogger(loggingContextFactory);
    }
}
