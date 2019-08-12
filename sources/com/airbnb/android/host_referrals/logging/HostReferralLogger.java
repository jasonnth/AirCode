package com.airbnb.android.host_referrals.logging;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSHostReferralActionEvent.Builder;

public class HostReferralLogger extends BaseLogger {
    public HostReferralLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logHostReferralAction(String target) {
        new Builder(context(), target);
    }
}
