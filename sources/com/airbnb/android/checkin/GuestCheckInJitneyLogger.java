package com.airbnb.android.checkin;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.jitney.event.logging.GuestCheckIn.p106v1.GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent;
import com.airbnb.jitney.event.logging.GuestCheckIn.p106v1.GuestCheckInCheckinGuideGuestTapBottomDotsEvent;
import com.airbnb.jitney.event.logging.GuestCheckIn.p107v2.GuestCheckInCheckinGuideGuestCheckinOkEvent.Builder;
import com.airbnb.jitney.event.logging.GuestCheckIn.p107v2.GuestCheckInCheckinGuideGuestClickPhotoInEvent;
import com.airbnb.jitney.event.logging.GuestCheckIn.p107v2.GuestCheckInCheckinGuideGuestClickPhotoOutEvent;
import com.airbnb.jitney.event.logging.GuestCheckIn.p107v2.GuestCheckInCheckinGuideGuestGetStartedEvent;
import com.airbnb.jitney.event.logging.GuestCheckIn.p107v2.GuestCheckInCheckinGuideGuestOpenMapEvent;
import com.airbnb.jitney.event.logging.GuestCheckIn.p107v2.GuestCheckInCheckinGuideGuestTakeScreenshotEvent;
import com.airbnb.jitney.event.logging.GuestCheckIn.p107v2.GuestCheckInCheckinGuideGuestTranslateEvent;
import com.airbnb.jitney.event.logging.GuestCheckIn.p107v2.GuestCheckInCheckinGuideGuestTranslateOriginalEvent;

public class GuestCheckInJitneyLogger extends BaseLogger {
    public GuestCheckInJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logCheckinGuideGuestCheckinOkEvent(long listingId) {
        publish(new Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideGuestClickPhotoInEvent(long listingId, long stepIndex) {
        publish(new GuestCheckInCheckinGuideGuestClickPhotoInEvent.Builder(context(), Long.valueOf(listingId), Long.valueOf(stepIndex)));
    }

    public void logCheckinGuideGuestClickPhotoOutEvent(long listingId, long stepIndex) {
        publish(new GuestCheckInCheckinGuideGuestClickPhotoOutEvent.Builder(context(), Long.valueOf(listingId), Long.valueOf(stepIndex)));
    }

    public void logCheckinGuideGuestGetStartedEvent(long listingId) {
        publish(new GuestCheckInCheckinGuideGuestGetStartedEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideGuestOpenMapEvent(long listingId) {
        publish(new GuestCheckInCheckinGuideGuestOpenMapEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideGuestTranslateEvent(long listingId, long stepIndex, String deviceLanguage, String guideLanguage) {
        publish(new GuestCheckInCheckinGuideGuestTranslateEvent.Builder(context(), Long.valueOf(listingId), Long.valueOf(stepIndex), deviceLanguage, guideLanguage));
    }

    public void logCheckinGuideGuestTranslateOriginalEvent(long listingId, long stepIndex, String deviceLanguage, String guideLanguage) {
        publish(new GuestCheckInCheckinGuideGuestTranslateOriginalEvent.Builder(context(), Long.valueOf(listingId), Long.valueOf(stepIndex), deviceLanguage, guideLanguage));
    }

    public void logCheckinGuideGuestTakeScreenshotEvent(long listingId, long stepIndex) {
        publish(new GuestCheckInCheckinGuideGuestTakeScreenshotEvent.Builder(context(), Long.valueOf(listingId), Long.valueOf(stepIndex)));
    }

    public void logCheckinGuideGuestSuccessfulLoadingOfflineEvent(long listingId, AirDateTime visibleStartTime) {
        publish(new GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent.Builder(context(), Long.valueOf(listingId), visibleStartTime.getIsoDateString()));
    }

    public void logCheckinGuideGuestTapBottomDotsEvent(long listingId, long stepIndex) {
        publish(new GuestCheckInCheckinGuideGuestTapBottomDotsEvent.Builder(context(), Long.valueOf(listingId), Long.valueOf(stepIndex)));
    }
}
