package com.airbnb.android.hostcalendar.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.p000v4.util.LongSparseArray;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.intents.HostCalendarIntents;
import com.airbnb.android.core.intents.HostReservationObjectIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.hostcalendar.models.AgendaOrganizer;
import com.airbnb.android.hostcalendar.models.ListingDayAgenda;
import com.airbnb.android.hostcalendar.viewmodels.CalendarAgendaListingRowEpoxyModel;
import com.airbnb.android.hostcalendar.views.CalendarAgendaInfoBlock.CalendarAgendaTapListener;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import java.util.List;

public class CalendarAgendaAdapter extends AirEpoxyAdapter {
    private final String agendaDateHeaderFormat;
    private final AgendaOrganizer agendaOrganizer = new AgendaOrganizer();
    private final CalendarAgendaTapListener calendarAgendaTapListener;
    private AirDate currentMaxAgendaDateDisplayed;
    private final InfiniteScrollListener infiniteScrollListener;
    private boolean isLoading;
    CalendarJitneyLogger jitneyLogger;
    private EpoxyModel<?> lastDateHeaderModel;
    private final DeprecatedCarouselEpoxyModel<CalendarThumbnailsAdapter> listingsCarousel = new DeprecatedCarouselEpoxyModel_();
    private final LoadingRowEpoxyModel_ loadingRowEpoxyModel = new LoadingRowEpoxyModel_();
    private boolean reachedTheEnd;
    private int reservationOffset = 0;
    private final AirDate thumbnailEndDate;
    private final AirDate thumbnailStartDate;
    private final AirDate today;
    private final String todaysHeaderWithFormat;

    public interface InfiniteScrollListener {
        void scrollForward(int i);
    }

    public CalendarAgendaAdapter(Context context, final Activity activity, InfiniteScrollListener infiniteScrollListener2) {
        ((HostCalendarGraph) CoreApplication.instance(context).component()).inject(this);
        this.agendaDateHeaderFormat = activity.getResources().getString(C6418R.string.hh_day_week_date_name_format);
        this.today = AirDate.today();
        this.thumbnailStartDate = this.today.getFirstDayOfMonth();
        this.thumbnailEndDate = this.thumbnailStartDate.getLastDayOfMonth();
        String todaysFormattedDate = this.today.formatDate(activity.getResources().getString(C6418R.string.date_name_format));
        this.todaysHeaderWithFormat = activity.getResources().getString(C6418R.string.today_with_formatted_date, new Object[]{todaysFormattedDate});
        this.infiniteScrollListener = infiniteScrollListener2;
        this.calendarAgendaTapListener = new CalendarAgendaTapListener() {
            public void onMessageClick(long listingId, long threadId) {
                CalendarAgendaAdapter.this.jitneyLogger.multiListingAgendaMessageClicked(listingId, threadId);
                activity.startActivity(ThreadFragmentIntents.newIntent(activity, threadId, InboxType.Host));
            }

            public void onReservationClick(long listingId, String confirmationCode) {
                CalendarAgendaAdapter.this.jitneyLogger.multiListingAgendaReservationClicked(listingId, confirmationCode);
                activity.startActivity(HostReservationObjectIntents.intentForConfirmationCode(activity, confirmationCode, ROLaunchSource.UNKNOWN));
            }
        };
        this.listingsCarousel.adapter(new CalendarThumbnailsAdapter(CalendarAgendaAdapter$$Lambda$1.lambdaFactory$(this, activity)));
        DocumentMarqueeEpoxyModel_ marquee = new DocumentMarqueeEpoxyModel_();
        marquee.titleRes(C6418R.string.calendar);
        marquee.captionText((CharSequence) this.thumbnailStartDate.formatDate(activity.getResources().getString(C6418R.string.full_month_format)));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{marquee, this.listingsCarousel});
    }

    static /* synthetic */ void lambda$new$0(CalendarAgendaAdapter calendarAgendaAdapter, Activity activity, Listing listing) {
        calendarAgendaAdapter.jitneyLogger.multiListingAgendaThumbnailClicked(listing.getId());
        activity.startActivity(HostCalendarIntents.intentForSingleListingCalendar(activity, listing.getId(), listing.getName()));
    }

    public void onBindViewHolder(EpoxyViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (!this.reachedTheEnd && !this.isLoading && position == this.models.indexOf(this.loadingRowEpoxyModel)) {
            scrollForward();
        }
    }

    public void initThumbnailCarousel(List<Listing> listings, List<Reservation> reservations, boolean showListingImage) {
        ((CalendarThumbnailsAdapter) this.listingsCarousel.adapter()).init(listings, reservations, this.thumbnailStartDate, showListingImage);
    }

    public void addListingsThumbnailCarousel(List<Listing> listings) {
        ((CalendarThumbnailsAdapter) this.listingsCarousel.adapter()).addListings(listings, this.thumbnailStartDate);
    }

    public int thumbnailCarouselSize() {
        return ((CalendarThumbnailsAdapter) this.listingsCarousel.adapter()).size();
    }

    public void addOrUpdateReservations(List<Reservation> reservations) {
        this.models.remove(this.loadingRowEpoxyModel);
        if (reservations.isEmpty()) {
            this.reachedTheEnd = true;
            if (this.reservationOffset == 0) {
                this.models.add(new SimpleTextRowEpoxyModel_().textRes(C6418R.string.host_calendar_agenda_none).showDivider(false));
                notifyDataSetChanged();
            } else if (this.currentMaxAgendaDateDisplayed != null) {
                addAgendaModels(this.currentMaxAgendaDateDisplayed.plusDays(1), false);
            }
        } else {
            this.agendaOrganizer.update(reservations, this.today);
            addAgendaModels(this.currentMaxAgendaDateDisplayed, true);
            this.reservationOffset += reservations.size();
        }
    }

    public void retryLoading() {
        this.models.add(this.loadingRowEpoxyModel);
        notifyDataSetChanged();
        scrollForward();
    }

    public void clear() {
        ((CalendarThumbnailsAdapter) this.listingsCarousel.adapter()).clear();
        removeAllAfterModel(this.listingsCarousel);
        this.reservationOffset = 0;
        this.reachedTheEnd = false;
        this.agendaOrganizer.clear();
        this.currentMaxAgendaDateDisplayed = null;
        notifyDataSetChanged();
    }

    public void endLoading() {
        this.models.remove(this.loadingRowEpoxyModel);
        this.isLoading = false;
        notifyDataSetChanged();
    }

    private void scrollForward() {
        this.isLoading = true;
        this.infiniteScrollListener.scrollForward(this.reservationOffset);
    }

    private void addAgendaModels(AirDate date, boolean hasMore) {
        AirDate endDate;
        if (!this.agendaOrganizer.isEmpty()) {
            if (date == null || date.isBefore(this.today)) {
                date = this.today;
            } else if (this.currentMaxAgendaDateDisplayed != null && date.isSameDayOrBefore(this.currentMaxAgendaDateDisplayed)) {
                date = this.currentMaxAgendaDateDisplayed;
                removeAllAfterModel(this.lastDateHeaderModel);
            } else if (this.currentMaxAgendaDateDisplayed != null && date.isAfter(this.currentMaxAgendaDateDisplayed)) {
                date = this.currentMaxAgendaDateDisplayed.plusDays(1);
            }
            if (hasMore) {
                endDate = this.agendaOrganizer.getMaxCheckinDate();
            } else {
                endDate = this.agendaOrganizer.getMaxDate();
            }
            while (date.isSameDayOrBefore(endDate)) {
                LongSparseArray<ListingDayAgenda> agendasByListingId = this.agendaOrganizer.get(date);
                if (agendasByListingId != null) {
                    addHeaderRow(date);
                    for (int i = 0; i < agendasByListingId.size(); i++) {
                        this.models.add(makeListingRow((ListingDayAgenda) agendasByListingId.get(agendasByListingId.keyAt(i))));
                    }
                }
                date = date.plusDays(1);
            }
            if (hasMore) {
                this.models.add(this.loadingRowEpoxyModel);
            }
            this.isLoading = false;
            notifyDataSetChanged();
        }
    }

    private void addHeaderRow(AirDate date) {
        if (this.currentMaxAgendaDateDisplayed == null || date.isAfter(this.currentMaxAgendaDateDisplayed)) {
            this.currentMaxAgendaDateDisplayed = date;
            this.lastDateHeaderModel = makeDateHeader(date);
            this.models.add(this.lastDateHeaderModel);
        }
    }

    private EpoxyModel<?> makeDateHeader(AirDate date) {
        SectionHeaderEpoxyModel_ model = new SectionHeaderEpoxyModel_();
        if (date.isSameDay(this.today)) {
            return model.title(this.todaysHeaderWithFormat);
        }
        if (date.isSameDay(this.today.plusDays(1))) {
            return model.titleRes(C6418R.string.tomorrow);
        }
        return model.title(date.formatDate(this.agendaDateHeaderFormat));
    }

    private EpoxyModel<?> makeListingRow(ListingDayAgenda listingDayAgenda) {
        return new CalendarAgendaListingRowEpoxyModel().listingName(listingDayAgenda.getListingName()).reservationStarting(listingDayAgenda.getCheckinReservation()).reservationOngoing(listingDayAgenda.getOngoingReservation()).reservationEnding(listingDayAgenda.getCheckoutReservation()).calendarAgendaTapListener(this.calendarAgendaTapListener);
    }

    public AirDate getThumbnailStartDate() {
        return this.thumbnailStartDate;
    }

    public AirDate getThumbnailEndDate() {
        return this.thumbnailEndDate;
    }
}
