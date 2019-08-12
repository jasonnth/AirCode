package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.jitney.event.logging.LYS.p130v2.LYSEnterListYourSpaceEvent.Builder;
import java.util.Calendar;

public class ListYourSpaceIntents {

    public static class DeepLinks {
        public static Intent deepLinkIntentForInProgressListing(Context context, Bundle extras) {
            long listingId = ManageListingIntents.getId(extras, "id");
            if (ManageListingIntents.isValidId(listingId)) {
                return ListYourSpaceIntents.intentForInProgressListing(context, listingId, "deeplink", null);
            }
            return ListYourSpaceIntents.intentForNewListing(context, "deeplink", null);
        }
    }

    public static Intent intentForInProgressListing(Context context, long listingId, String page, String target) {
        String sessionId = generateSessionId();
        logEnterEvent(listingId, sessionId, page, target);
        return new Intent(context, Activities.listYourSpaceDLS()).putExtra("listing_id", listingId).putExtra(ManageListingIntents.INTENT_EXTRA_SESSION_ID, sessionId);
    }

    public static Intent intentForNewListing(Context context, String page, String target) {
        return intentForInProgressListing(context, -1, page, target);
    }

    private static void logEnterEvent(long listingId, String sessionId, String page, String target) {
        if (target == null) {
            target = "";
        }
        JitneyPublisher.publish(new Builder(CoreApplication.instance().component().loggingContextFactory().newInstance(), page, target, sessionId).listing_id(Long.valueOf(listingId)));
    }

    private static String generateSessionId() {
        return String.valueOf(CoreApplication.instance().component().accountManager().getCurrentUserId()) + Calendar.getInstance().getTimeInMillis();
    }
}
