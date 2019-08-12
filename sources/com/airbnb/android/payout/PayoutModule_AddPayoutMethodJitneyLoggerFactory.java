package com.airbnb.android.payout;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.payout.logging.AddPayoutMethodJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class PayoutModule_AddPayoutMethodJitneyLoggerFactory implements Factory<AddPayoutMethodJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PayoutModule_AddPayoutMethodJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public PayoutModule_AddPayoutMethodJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public AddPayoutMethodJitneyLogger get() {
        return (AddPayoutMethodJitneyLogger) Preconditions.checkNotNull(PayoutModule.addPayoutMethodJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AddPayoutMethodJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new PayoutModule_AddPayoutMethodJitneyLoggerFactory(loggingContextFactoryProvider2);
    }
}
