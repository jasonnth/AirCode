package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSEnterHostReferralEvent.Builder;

public final class HostReferralsIntents {
    private HostReferralsIntents() {
    }

    public static Intent newIntentForHostReferrals(Context context, String page) {
        logEnterHostReferralEvent(page);
        return new Intent(context, Activities.hostReferrals());
    }

    private static void logEnterHostReferralEvent(String page) {
        JitneyPublisher.publish(new Builder(CoreApplication.instance().component().loggingContextFactory().newInstance(), page));
    }
}
