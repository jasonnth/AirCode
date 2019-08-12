package com.airbnb.android.lib.fragments.inbox.utils;

import android.content.Context;
import com.airbnb.android.core.models.MagicalTripAttachment;
import com.airbnb.android.core.models.MagicalTripAttachmentDetails;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.lib.C0880R;

public class CohostingDisplayUtil {
    public static String generateNamesString(Context context, Thread thread) {
        long listingId = getListingId(thread);
        if (listingId == -1 || listingId == 0) {
            return null;
        }
        return context.getString(C0880R.string.cohosting_message_thread_status);
    }

    public static long getListingId(Thread thread) {
        MagicalTripAttachment attachment = thread.getAttachment();
        if (attachment == null) {
            return -1;
        }
        MagicalTripAttachmentDetails details = attachment.getDetails();
        if (details != null) {
            return details.getListingId();
        }
        return -1;
    }
}
