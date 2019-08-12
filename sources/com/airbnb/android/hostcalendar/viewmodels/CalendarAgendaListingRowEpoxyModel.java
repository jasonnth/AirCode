package com.airbnb.android.hostcalendar.viewmodels;

import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.CalendarAgendaInfoBlock.CalendarAgendaTapListener;
import com.airbnb.android.hostcalendar.views.CalendarAgendaListingRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public class CalendarAgendaListingRowEpoxyModel extends AirEpoxyModel<CalendarAgendaListingRow> {
    private CalendarAgendaTapListener listener;
    private String listingName;
    private Reservation reservationEnding;
    private Reservation reservationOngoing;
    private Reservation reservationStarting;

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C6418R.layout.view_holder_calendar_agenda_listing_row;
    }

    public void bind(CalendarAgendaListingRow view) {
        super.bind(view);
        view.bindData(this.listingName, this.reservationEnding, this.reservationOngoing, this.reservationStarting, this.listener);
    }

    public CalendarAgendaListingRowEpoxyModel listingName(String name) {
        this.listingName = name;
        return this;
    }

    public CalendarAgendaListingRowEpoxyModel reservationStarting(Reservation reservation) {
        this.reservationStarting = reservation;
        return this;
    }

    public CalendarAgendaListingRowEpoxyModel reservationOngoing(Reservation reservation) {
        this.reservationOngoing = reservation;
        return this;
    }

    public CalendarAgendaListingRowEpoxyModel reservationEnding(Reservation reservation) {
        this.reservationEnding = reservation;
        return this;
    }

    public CalendarAgendaListingRowEpoxyModel calendarAgendaTapListener(CalendarAgendaTapListener listener2) {
        this.listener = listener2;
        return this;
    }
}
