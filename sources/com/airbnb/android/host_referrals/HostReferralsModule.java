package com.airbnb.android.host_referrals;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.host_referrals.logging.HostReferralLogger;

public class HostReferralsModule {
    public static HostReferralLogger provideHostReferralsLogger(LoggingContextFactory loggingContextFactory) {
        return new HostReferralLogger(loggingContextFactory);
    }
}
