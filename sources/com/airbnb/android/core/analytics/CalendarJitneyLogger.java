package com.airbnb.android.core.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarEditSheetChangeAvailabilityEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarEditSheetChangePriceEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarEditSheetChangeSmartPricingEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarEditSheetTapNotesEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarMultiListingAgendaLoadMoreEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarMultiListingAgendaMessageEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarMultiListingAgendaReservationEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarMultiListingListCalThumbnailTapEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarNoteSaveEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarSingleListingAgendaEditDatesEvent.Builder;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarSingleListingAgendaLoadMoreEvent;
import com.airbnb.jitney.event.logging.Calendar.p045v1.CalendarSingleListingMonthEditDatesEvent;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;

public class CalendarJitneyLogger extends BaseLogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public CalendarJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void singleListingAgendaDateClicked(Long listing_id, ArrayList<CalendarDay> days) {
        publish(new Builder(listing_id, getDates(days), context()));
    }

    public void singleListingMonthDateClicked(Long listing_id, ArrayList<CalendarDay> days) {
        publish(new CalendarSingleListingMonthEditDatesEvent.Builder(context(), listing_id, getDates(days)));
    }

    public void noteSaveButtonClicked(Long listing_id, ArrayList<CalendarDay> days, boolean isNewNote) {
        publish(new CalendarNoteSaveEvent.Builder(context(), listing_id, getDates(days), isNewNote ? "Add" : "Edit"));
    }

    public void editSheetNotesClicked(long listing_id, ArrayList<CalendarDay> days, boolean isNewNote) {
        publish(new CalendarEditSheetTapNotesEvent.Builder(context(), Long.valueOf(listing_id), getDates(days), isNewNote ? "Add" : "Edit"));
    }

    public void editSheetNotesClickedForSingleDay(long listing_id, CalendarDay day, boolean isNewNote) {
        ArrayList<CalendarDay> calendarDays = new ArrayList<>();
        calendarDays.add(day);
        editSheetNotesClicked(listing_id, calendarDays, isNewNote);
    }

    public void editSheetChangePriceSaved(long listing_id, ArrayList<CalendarDay> days, long nightly_price) {
        publish(new CalendarEditSheetChangePriceEvent.Builder(context(), Long.valueOf(listing_id), getDates(days), Long.valueOf(nightly_price)));
    }

    public void editSheetChangeSmartPricingSaved(long listing_id, ArrayList<CalendarDay> days, boolean smart_pricing_on) {
        publish(new CalendarEditSheetChangeSmartPricingEvent.Builder(context(), Long.valueOf(listing_id), getDates(days), Boolean.valueOf(smart_pricing_on)));
    }

    public void editSheetChangeAvailabilitySaved(long listing_id, ArrayList<CalendarDay> days, boolean available) {
        publish(new CalendarEditSheetChangeAvailabilityEvent.Builder(context(), Long.valueOf(listing_id), getDates(days), Boolean.valueOf(available)));
    }

    public void multiListingAgendaThumbnailClicked(long listingId) {
        publish(new CalendarMultiListingListCalThumbnailTapEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void multiListingAgendaMessageClicked(long listingId, long messageThreadId) {
        publish(new CalendarMultiListingAgendaMessageEvent.Builder(context(), Long.valueOf(listingId), Long.valueOf(messageThreadId)));
    }

    public void multiListingAgendaReservationClicked(long listingId, String confirmationCode) {
        publish(new CalendarMultiListingAgendaReservationEvent.Builder(context(), Long.valueOf(listingId), confirmationCode));
    }

    public void multiListingAgendaLoadMore() {
        publish(new CalendarMultiListingAgendaLoadMoreEvent.Builder(context()));
    }

    public void singleListingAgendaLoadMore(long listingId) {
        publish(new CalendarSingleListingAgendaLoadMoreEvent.Builder(context(), Long.valueOf(listingId)));
    }

    private static ArrayList<String> getDates(ArrayList<CalendarDay> days) {
        return new ArrayList<>(FluentIterable.from((Iterable<E>) days).transform(CalendarJitneyLogger$$Lambda$1.lambdaFactory$()).toList());
    }
}
