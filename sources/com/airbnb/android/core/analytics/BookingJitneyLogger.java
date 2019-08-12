package com.airbnb.android.core.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.jitney.event.logging.ChinaGuestNameSection.p056v1.C1923ChinaGuestNameSection;
import com.airbnb.jitney.event.logging.IdType.p117v1.C2217IdType;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowBookingCheckinSelectTimeEvent.Builder;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowBookingDatepickerSelectDatesEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowBookingSummaryClickEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowBookingSummaryImpressionEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowBookingSummaryToggleBtEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowChinaGuestAddInfoEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowChinaGuestDeleteInfoEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowChinaGuestFocusNameSectionEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowChinaGuestSelectIdTypeEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowChinaGuestSelectNationalityEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowClickNavigationEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowGuestSheetSelectGuestsEvent;
import com.airbnb.jitney.event.logging.MobileP4Flow.p152v1.MobileP4FlowGuestSheetTogglePetsEvent;
import com.airbnb.jitney.event.logging.P4FlowDatepickerSection.p169v1.C2463P4FlowDatepickerSection;
import com.airbnb.jitney.event.logging.P4FlowGuestSheetMethod.p170v1.C2464P4FlowGuestSheetMethod;
import com.airbnb.jitney.event.logging.P4FlowGuestSheetSection.p171v1.C2465P4FlowGuestSheetSection;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.jitney.event.logging.P4FlowSummarySection.p175v1.C2469P4FlowSummarySection;
import com.airbnb.jitney.event.logging.ToggleMethod.p268v1.C2759ToggleMethod;
import com.airbnb.jitney.event.logging.UcMessageType.p276v1.C2774UcMessageType;

public class BookingJitneyLogger extends BaseLogger {
    public BookingJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    private long getReservationId(ReservationDetails reservationDetails) {
        if (reservationDetails.reservationId() == null) {
            return 0;
        }
        return reservationDetails.reservationId().longValue();
    }

    public void checkinSelectTime(ReservationDetails reservationDetails, boolean isInstantBook) {
        publish(new Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), Long.valueOf(getReservationId(reservationDetails)), Boolean.valueOf(isInstantBook)));
    }

    public void datePickerSelectDates(String date, ReservationDetails reservationDetails, boolean isInstantBook, C2463P4FlowDatepickerSection section) {
        MobileP4FlowBookingDatepickerSelectDatesEvent.Builder builder;
        MobileP4FlowBookingDatepickerSelectDatesEvent.Builder builder2 = new MobileP4FlowBookingDatepickerSelectDatesEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), Long.valueOf(getReservationId(reservationDetails)), Boolean.valueOf(isInstantBook), section);
        if (section == C2463P4FlowDatepickerSection.Checkin) {
            builder = builder2.checkin_date(date);
        } else {
            builder = builder2.checkout_date(date);
        }
        publish(builder);
    }

    public void bookingSummaryClick(ReservationDetails reservationDetails, boolean isInstantBook, C2469P4FlowSummarySection section) {
        publish(new MobileP4FlowBookingSummaryClickEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), Long.valueOf(getReservationId(reservationDetails)), Boolean.valueOf(isInstantBook), section));
    }

    public void chinaGuestAddInfo(ReservationDetails reservationDetails, boolean isInstantBook) {
        publish(new MobileP4FlowChinaGuestAddInfoEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), reservationDetails.reservationId(), Boolean.valueOf(isInstantBook)));
    }

    public void chinaGuestDeleteInfo(ReservationDetails reservationDetails, boolean isInstantBook) {
        publish(new MobileP4FlowChinaGuestDeleteInfoEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), reservationDetails.reservationId(), Boolean.valueOf(isInstantBook)));
    }

    public void chinaGuestFocusNameSection(ReservationDetails reservationDetails, boolean isInstantBook, C1923ChinaGuestNameSection section) {
        publish(new MobileP4FlowChinaGuestFocusNameSectionEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), reservationDetails.reservationId(), Boolean.valueOf(isInstantBook), section));
    }

    public void chinaGuestSelectIdType(ReservationDetails reservationDetails, boolean isInstantBook, C2217IdType idType) {
        publish(new MobileP4FlowChinaGuestSelectIdTypeEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), reservationDetails.reservationId(), Boolean.valueOf(isInstantBook), idType));
    }

    public void chinaGuestSelectNationality(ReservationDetails reservationDetails, boolean isInstantBook, String countryCode) {
        publish(new MobileP4FlowChinaGuestSelectNationalityEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), reservationDetails.reservationId(), Boolean.valueOf(isInstantBook), countryCode));
    }

    public void clickNavigation(ReservationDetails reservationDetails, boolean isInstantBook, C2467P4FlowPage page, C2466P4FlowNavigationMethod method) {
        if (reservationDetails.reservationId() != null) {
            publish(new MobileP4FlowClickNavigationEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), reservationDetails.reservationId(), Boolean.valueOf(isInstantBook), page, method));
        }
    }

    public void guestSheetSelectGuests(ReservationDetails reservationDetails, boolean isInstantBook, C2465P4FlowGuestSheetSection section, C2464P4FlowGuestSheetMethod method) {
        publish(new MobileP4FlowGuestSheetSelectGuestsEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), Long.valueOf(getReservationId(reservationDetails)), Boolean.valueOf(isInstantBook), section, method));
    }

    public void guestSheetTogglePets(ReservationDetails reservationDetails, boolean isInstantBook, C2759ToggleMethod method) {
        publish(new MobileP4FlowGuestSheetTogglePetsEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), Long.valueOf(getReservationId(reservationDetails)), Boolean.valueOf(isInstantBook), method));
    }

    public void businessTravelToggle(ReservationDetails reservationDetails, boolean isInstantBook, C2759ToggleMethod method) {
        publish(new MobileP4FlowBookingSummaryToggleBtEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), String.valueOf(getReservationId(reservationDetails)), Boolean.valueOf(isInstantBook), method));
    }

    public void bookingSummaryUCImpression(ReservationDetails reservationDetails, boolean isInstantBook, C2774UcMessageType jitneyUrgencyType) {
        MobileP4FlowBookingSummaryImpressionEvent.Builder builder = new MobileP4FlowBookingSummaryImpressionEvent.Builder(context(), reservationDetails.guestId(), reservationDetails.listingId(), String.valueOf(getReservationId(reservationDetails)), Boolean.valueOf(isInstantBook));
        if (jitneyUrgencyType != null) {
            builder = builder.uc_message(jitneyUrgencyType);
        }
        publish(builder);
    }
}
