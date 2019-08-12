package com.airbnb.android.core.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.jitney.event.logging.ReservationObject.p226v1.ReservationObjectAcceptConfirmationEvent;
import com.airbnb.jitney.event.logging.ReservationObject.p226v1.ReservationObjectAcceptEvent.Builder;
import com.airbnb.jitney.event.logging.ReservationObject.p226v1.ReservationObjectRemovePreapproveConfirmationEvent;
import com.airbnb.jitney.event.logging.ReservationObject.p227v2.ReservationObjectDeclineEvent;
import com.airbnb.jitney.event.logging.ReservationObject.p227v2.ReservationObjectMessageEvent;
import com.airbnb.jitney.event.logging.ReservationObject.p227v2.ReservationObjectPreapproveConfirmationEvent;
import com.airbnb.jitney.event.logging.ReservationObject.p227v2.ReservationObjectPreapproveEvent;
import com.airbnb.jitney.event.logging.ReservationObject.p227v2.ReservationObjectRemovePreapproveEvent;

public class HostReservationObjectJitneyLogger extends BaseLogger {
    public HostReservationObjectJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void reservationObjectAccept(TripInformationProvider provider) {
        publish(new Builder(context(), Long.valueOf(provider.getListing().getId()), Long.valueOf(provider.getReservation().getId())));
    }

    public void reservationObjectDecline(TripInformationProvider provider) {
        ReservationObjectDeclineEvent.Builder builder = new ReservationObjectDeclineEvent.Builder(context(), Long.valueOf(provider.getListing().getId()), provider.hasReservation() ? "reservation" : "inquiry").message_thread_id(Long.valueOf(provider.getThreadId()));
        if (provider.hasReservation()) {
            builder.reservation_id(Long.valueOf(provider.getReservation().getId()));
        }
        publish(builder);
    }

    public void reservationObjectMessage(TripInformationProvider provider) {
        ReservationObjectMessageEvent.Builder builder = new ReservationObjectMessageEvent.Builder(context(), Long.valueOf(provider.getListing().getId()), provider.hasReservation() ? "reservation" : "inquiry").message_thread_id(Long.valueOf(provider.getThreadId()));
        if (provider.hasReservation()) {
            builder.reservation_id(Long.valueOf(provider.getReservation().getId()));
        }
        publish(builder);
    }

    public void reservationObjectPreapprove(TripInformationProvider provider) {
        publish(new ReservationObjectPreapproveEvent.Builder(context(), Long.valueOf(provider.getListing().getId()), Long.valueOf(provider.getThreadId())));
    }

    public void reservationObjectRemovePreapprove(TripInformationProvider provider) {
        publish(new ReservationObjectRemovePreapproveEvent.Builder(context(), Long.valueOf(provider.getListing().getId()), Long.valueOf(provider.getThreadId())));
    }

    public void reservationObjectAcceptConfirmation(TripInformationProvider provider) {
        publish(new ReservationObjectAcceptConfirmationEvent.Builder(context(), Long.valueOf(provider.getListing().getId()), Long.valueOf(provider.getReservation().getId())));
    }

    public void reservationObjectPreapproveConfirmation(TripInformationProvider provider) {
        publish(new ReservationObjectPreapproveConfirmationEvent.Builder(context(), Long.valueOf(provider.getListing().getId()), Long.valueOf(provider.getThreadId())));
    }

    public void reservationObjectRemovePreapproveConfirmation(TripInformationProvider provider) {
        publish(new ReservationObjectRemovePreapproveConfirmationEvent.Builder(context(), Long.valueOf(provider.getListing().getId()), Long.valueOf(provider.getThreadId())));
    }
}
