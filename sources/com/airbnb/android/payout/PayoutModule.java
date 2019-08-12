package com.airbnb.android.payout;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.payout.logging.AddPayoutMethodJitneyLogger;

public class PayoutModule {
    public static AddPayoutMethodJitneyLogger addPayoutMethodJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new AddPayoutMethodJitneyLogger(loggingContextFactory);
    }
}
