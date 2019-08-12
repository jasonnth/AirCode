package com.airbnb.android.lib.fragments.reservationresponse;

import android.text.TextUtils;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.jitney.event.logging.DeclineFlow.p079v0.DeclineFlowEditSaveEvent;
import com.airbnb.jitney.event.logging.DeclineFlow.p079v0.DeclineFlowTipActionEvent;
import com.airbnb.jitney.event.logging.DeclineFlow.p079v0.DeclineFlowTipClickEvent;
import com.airbnb.jitney.event.logging.DeclineFlow.p081v3.DeclineFlowPickReasonEvent;
import com.airbnb.jitney.event.logging.ReservationObject.p225v0.ReservationObjectDeclineClickFinalEvent;
import com.airbnb.jitney.event.logging.ReservationObject.p227v2.ReservationObjectDeclineEvent.Builder;

public class ReservationResponseLogger extends BaseLogger {

    public interface ReservationResponseDataProvider {
        DeclineReason getDeclineReason();

        Reservation getReservation();

        Long getThreadId();
    }

    public ReservationResponseLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void onReservationDeclined(ReservationResponseDataProvider provider) {
        Reservation reservation = provider.getReservation();
        publish(new Builder(context(), Long.valueOf(reservation.getListing().getId()), "reservation").reservation_id(Long.valueOf(reservation.getId())).message_thread_id(provider.getThreadId()));
    }

    public void onMessageSaveSelected(ReservationResponseDataProvider provider, String message, boolean isAirbnbMessage) {
        Reservation reservation = provider.getReservation();
        publish(new DeclineFlowEditSaveEvent.Builder(context(), Long.valueOf(reservation.getListing().getId()), Long.valueOf(reservation.getId())).message_thread_id(provider.getThreadId()).page(isAirbnbMessage ? "airbnb_message" : "personal_message").message_length(Long.valueOf((long) (TextUtils.isEmpty(message) ? 0 : message.length()))));
    }

    public void onBookingTipSelected(ReservationResponseDataProvider provider, String key) {
        Reservation reservation = provider.getReservation();
        publish(new DeclineFlowTipActionEvent.Builder(context(), Long.valueOf(reservation.getListing().getId()), Long.valueOf(reservation.getId()), key).message_thread_id(provider.getThreadId()));
    }

    public void onShowTipsSelected(ReservationResponseDataProvider provider) {
        Reservation reservation = provider.getReservation();
        publish(new DeclineFlowTipClickEvent.Builder(context(), Long.valueOf(reservation.getListing().getId()), provider.getThreadId(), provider.getDeclineReason().serverKey).reservation_id(Long.valueOf(reservation.getId())));
    }

    public void onDeclineSelected(ReservationResponseDataProvider provider) {
        Reservation reservation = provider.getReservation();
        publish(new ReservationObjectDeclineClickFinalEvent.Builder(context(), provider.getDeclineReason().serverKey, Long.valueOf(reservation.getListing().getId()), Long.valueOf(reservation.getId())));
    }

    public void onDeclineReasonSelected(ReservationResponseDataProvider provider, DeclineReason reason) {
        Reservation reservation = provider.getReservation();
        publish(new DeclineFlowPickReasonEvent.Builder(context(), Long.valueOf(reservation.getListing().getId()), Long.valueOf(reservation.getId()), reason.serverKey).message_thread_id(provider.getThreadId()));
    }
}
