package com.airbnb.android.hostcalendar.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.CalendarMiniThumbnail;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;

public class CalendarMiniThumbnailEpoxyModel extends AirEpoxyModel<CalendarMiniThumbnail> {
    private OnClickListener clickListener;
    private Listing listing;
    ArrayList<Reservation> reservations;
    private boolean showListingImage;
    private AirDate startDate;
    private AirDate today;

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C6418R.layout.view_holder_calendar_mini_thumbnail;
    }

    public void bind(CalendarMiniThumbnail view) {
        super.bind(view);
        view.bind(this.listing, this.startDate, this.today, this.reservations, this.showListingImage);
        view.setOnClickListener(this.clickListener);
    }

    public CalendarMiniThumbnailEpoxyModel listing(Listing listing2) {
        this.listing = listing2;
        return this;
    }

    public CalendarMiniThumbnailEpoxyModel startDate(AirDate startDate2) {
        this.startDate = startDate2;
        return this;
    }

    public CalendarMiniThumbnailEpoxyModel today(AirDate today2) {
        this.today = today2;
        return this;
    }

    public CalendarMiniThumbnailEpoxyModel clickListener(OnClickListener clickListener2) {
        this.clickListener = clickListener2;
        return this;
    }

    public CalendarMiniThumbnailEpoxyModel reservations(ArrayList<Reservation> reservations2) {
        this.reservations = reservations2;
        return this;
    }

    public CalendarMiniThumbnailEpoxyModel showListingImage(boolean showListingImage2) {
        this.showListingImage = showListingImage2;
        return this;
    }
}
