package com.airbnb.android.managelisting.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideAddCheckinMethodEvent.Builder;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideCreateEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideCreateStepNoteEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideCreateStepPhotoEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideEditCheckinMethodInfoEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideFetchEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideMoreOptionsEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideRemoveCheckinMethodEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideSaveCheckinMethodEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideToolbarEditMethodsEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideToolbarPreviewEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideToolbarPublishEvent;
import com.airbnb.jitney.event.logging.CheckIn.p054v1.CheckInCheckinGuideToolbarUnpublishEvent;
import com.airbnb.jitney.event.logging.CheckIn.p055v2.CheckInCheckinGuideDeleteStepEvent;
import com.airbnb.jitney.event.logging.CheckIn.p055v2.CheckInCheckinGuideUpdateStepNoteEvent;
import com.airbnb.jitney.event.logging.CheckIn.p055v2.CheckInCheckinGuideUpdateStepPhotoEvent;

public class CheckInJitneyLogger extends BaseLogger {
    public CheckInJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logCheckinGuideAddCheckinMethodEvent(long amenityId, long listingId) {
        publish(new Builder(context(), Long.valueOf(amenityId), Long.valueOf(listingId)));
    }

    public void logCheckinGuideRemoveCheckinMethodEvent(long amenityId, long listingId) {
        publish(new CheckInCheckinGuideRemoveCheckinMethodEvent.Builder(context(), Long.valueOf(amenityId), Long.valueOf(listingId)));
    }

    public void logCheckinGuideSaveCheckinMethodEvent(long listingId) {
        publish(new CheckInCheckinGuideSaveCheckinMethodEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideEditCheckinMethodInfoEvent(long amenityId, long listingId) {
        publish(new CheckInCheckinGuideEditCheckinMethodInfoEvent.Builder(context(), Long.valueOf(amenityId), Long.valueOf(listingId)));
    }

    public void logCheckinGuideCreateEvent(long listingId) {
        publish(new CheckInCheckinGuideCreateEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideCreateStepNoteEvent(long listingId) {
        publish(new CheckInCheckinGuideCreateStepNoteEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideUpdateStepNoteEvent(long stepId, long listingId) {
        publish(new CheckInCheckinGuideUpdateStepNoteEvent.Builder(context(), Long.valueOf(stepId), Long.valueOf(listingId)));
    }

    public void logCheckinGuideCreateStepPhotoEvent(long listingId) {
        publish(new CheckInCheckinGuideCreateStepPhotoEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideUpdateStepPhotoEvent(long stepId, long listingId) {
        publish(new CheckInCheckinGuideUpdateStepPhotoEvent.Builder(context(), Long.valueOf(stepId), Long.valueOf(listingId)));
    }

    public void logCheckinGuideDeleteStepEvent(long stepId, long listingId) {
        publish(new CheckInCheckinGuideDeleteStepEvent.Builder(context(), Long.valueOf(stepId), Long.valueOf(listingId)));
    }

    public void logCheckinGuideFetchEvent(long listingId) {
        publish(new CheckInCheckinGuideFetchEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideMoreOptionsEvent(long listingId) {
        publish(new CheckInCheckinGuideMoreOptionsEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideToolbarEditMethodsEvent(long listingId) {
        publish(new CheckInCheckinGuideToolbarEditMethodsEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideToolbarPreviewEvent(long listingId) {
        publish(new CheckInCheckinGuideToolbarPreviewEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideToolbarPublishEvent(long listingId) {
        publish(new CheckInCheckinGuideToolbarPublishEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logCheckinGuideToolbarUnpublishEvent(long listingId) {
        publish(new CheckInCheckinGuideToolbarUnpublishEvent.Builder(context(), Long.valueOf(listingId)));
    }
}
