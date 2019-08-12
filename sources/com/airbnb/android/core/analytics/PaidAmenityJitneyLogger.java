package com.airbnb.android.core.analytics;

import android.util.Log;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesGuestAddClickFinalizeAnotherEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesGuestAddClickFinalizeExitEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesGuestAddClickFinalizeMessageEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesGuestAddClickReviewEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesGuestAddClickServiceEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesGuestAddClickShowServicesEvent.Builder;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesGuestAmendClickCancelEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesGuestAmendClickExistingEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesHostAddClickCommitEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesHostAddClickServiceEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesHostAddClickSetPriceEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesHostAmendClickDeleteEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesHostFulfillClickAcceptEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesHostFulfillClickCancelEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesHostFulfillClickDeclineEvent;
import com.airbnb.jitney.event.logging.PaidAmenities.p176v1.PaidAmenitiesHostFulfillClickServiceEvent;
import com.airbnb.jitney.event.logging.PaidAmenitiesContext.p177v1.C2491PaidAmenitiesContext;
import com.airbnb.jitney.event.logging.PaidAmenitiesOrderContext.p178v1.C2493PaidAmenitiesOrderContext;

public class PaidAmenityJitneyLogger extends BaseLogger {
    public PaidAmenityJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logGuestAddClickShowServices() {
        publish(new Builder(context(), paidAmenitiesOrderContext()));
    }

    public void logGuestAddClickService() {
        publish(new PaidAmenitiesGuestAddClickServiceEvent.Builder(context(), paidAmenitiesOrderContext()));
    }

    public void logGuestAddClickReview() {
        publish(new PaidAmenitiesGuestAddClickReviewEvent.Builder(context(), paidAmenitiesOrderContext()));
    }

    public void logGuestAddClickFinalizeMessage() {
        publish(new PaidAmenitiesGuestAddClickFinalizeMessageEvent.Builder(context(), paidAmenitiesOrderContext()));
    }

    public void logGuestAddClickFinalizeAnother() {
        publish(new PaidAmenitiesGuestAddClickFinalizeAnotherEvent.Builder(context(), "", paidAmenitiesOrderContext()));
    }

    public void logGuestAddClickFinalizeExit() {
        publish(new PaidAmenitiesGuestAddClickFinalizeExitEvent.Builder(context(), paidAmenitiesOrderContext()));
    }

    public void logGuestAmendClickExisting(long paidAmenityOrderId) {
        publish(new PaidAmenitiesGuestAmendClickExistingEvent.Builder(context(), Long.valueOf(paidAmenityOrderId)));
    }

    public void logGuestAmendClickCancel(long paidAmenityOrderId) {
        publish(new PaidAmenitiesGuestAmendClickCancelEvent.Builder(context(), Long.valueOf(paidAmenityOrderId)));
    }

    public void logHostAddClickService() {
        Log.d("paid amenity", "confirm");
        publish(new PaidAmenitiesHostAddClickServiceEvent.Builder(context(), paidAmenitiesContext()));
    }

    public void logHostAddClickSetPrice() {
        publish(new PaidAmenitiesHostAddClickSetPriceEvent.Builder(context(), paidAmenitiesContext()));
    }

    public void logHostAddClickFinalize() {
        publish(new PaidAmenitiesHostAddClickCommitEvent.Builder(context(), paidAmenitiesContext()));
    }

    public void logHostAmendClickDelete(long paidAmenityId) {
        publish(new PaidAmenitiesHostAmendClickDeleteEvent.Builder(context(), Long.valueOf(paidAmenityId)));
    }

    public void logHostFulfillClickService(long paidAmenityOrderId) {
        publish(new PaidAmenitiesHostFulfillClickServiceEvent.Builder(context(), "", Long.valueOf(paidAmenityOrderId)));
    }

    public void logHostFulfillClickAccept(long paidAmenityOrderId) {
        publish(new PaidAmenitiesHostFulfillClickAcceptEvent.Builder(context(), Long.valueOf(paidAmenityOrderId)));
    }

    public void logHostFulfillClickDecline(long paidAmenityOrderId) {
        publish(new PaidAmenitiesHostFulfillClickDeclineEvent.Builder(context(), Long.valueOf(paidAmenityOrderId)));
    }

    public void logHostFulfillClickCancel(long paidAmenityOrderId) {
        publish(new PaidAmenitiesHostFulfillClickCancelEvent.Builder(context(), Long.valueOf(paidAmenityOrderId)));
    }

    private C2491PaidAmenitiesContext paidAmenitiesContext() {
        return new C2491PaidAmenitiesContext.Builder().build();
    }

    private C2493PaidAmenitiesOrderContext paidAmenitiesOrderContext() {
        return new C2493PaidAmenitiesOrderContext.Builder().build();
    }
}
