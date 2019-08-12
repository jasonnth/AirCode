package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.MagicalTripAttachment;
import com.airbnb.android.core.models.MagicalTripAttachmentDetails;
import com.airbnb.android.core.models.MagicalTripAttachmentType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.ThreadType;
import com.airbnb.android.core.models.TripTemplate.Type;

public class MagicalTripsThreadUtil {
    public static Intent getIntentForDetails(Context context, Thread thread, boolean isGuest) {
        ThreadType threadType = thread.getThreadType();
        MagicalTripAttachment attachment = thread.getAttachment();
        MagicalTripAttachmentDetails details = attachment.getDetails();
        long tripId = details.getTripId();
        long scheduledTemplateId = details.getScheduledTemplateId();
        if (isGuest && tripId != 0) {
            return ReactNativeIntents.intentForItineraryImmersion(context, tripId);
        }
        if (!isGuest && scheduledTemplateId != 0) {
            return ReactNativeIntents.intentForCityHostsScheduledTemplate(context, scheduledTemplateId);
        }
        if (!isGuest && attachment.getTypeEnum() != MagicalTripAttachmentType.ExperienceInquiry) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("MT host tapped Details on a post booking thread but it's missing scheduledTemplateId"));
        }
        if (isGuest && attachment.getTypeEnum() == MagicalTripAttachmentType.Trip && threadType == ThreadType.TripDirect) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("MT guest tapped Details on a post booking direct thread but it's missing tripId"));
        }
        return ReactNativeIntents.intentForExperiencePDP(context, details.getTemplateProductTypeEnum() == Type.Immersion, details.getTemplateId());
    }
}
