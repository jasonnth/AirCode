package com.airbnb.android.hostcalendar.adapters;

import android.app.Activity;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.hostcalendar.adapters.CalendarThumbnailsAdapter.CalendarThumbnailClickListener;

final /* synthetic */ class CalendarAgendaAdapter$$Lambda$1 implements CalendarThumbnailClickListener {
    private final CalendarAgendaAdapter arg$1;
    private final Activity arg$2;

    private CalendarAgendaAdapter$$Lambda$1(CalendarAgendaAdapter calendarAgendaAdapter, Activity activity) {
        this.arg$1 = calendarAgendaAdapter;
        this.arg$2 = activity;
    }

    public static CalendarThumbnailClickListener lambdaFactory$(CalendarAgendaAdapter calendarAgendaAdapter, Activity activity) {
        return new CalendarAgendaAdapter$$Lambda$1(calendarAgendaAdapter, activity);
    }

    public void onClick(Listing listing) {
        CalendarAgendaAdapter.lambda$new$0(this.arg$1, this.arg$2, listing);
    }
}
