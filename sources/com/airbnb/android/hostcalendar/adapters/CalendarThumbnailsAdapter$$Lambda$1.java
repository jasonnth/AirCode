package com.airbnb.android.hostcalendar.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;

final /* synthetic */ class CalendarThumbnailsAdapter$$Lambda$1 implements OnClickListener {
    private final CalendarThumbnailsAdapter arg$1;
    private final Listing arg$2;

    private CalendarThumbnailsAdapter$$Lambda$1(CalendarThumbnailsAdapter calendarThumbnailsAdapter, Listing listing) {
        this.arg$1 = calendarThumbnailsAdapter;
        this.arg$2 = listing;
    }

    public static OnClickListener lambdaFactory$(CalendarThumbnailsAdapter calendarThumbnailsAdapter, Listing listing) {
        return new CalendarThumbnailsAdapter$$Lambda$1(calendarThumbnailsAdapter, listing);
    }

    public void onClick(View view) {
        this.arg$1.listener.onClick(this.arg$2);
    }
}
