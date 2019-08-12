package com.airbnb.android.hostcalendar.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.hostcalendar.views.HostCalendarReservationView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class HostCalendarReservationEpoxyModel extends AirEpoxyModel<HostCalendarReservationView> {
    private CalendarDays calendarDays;
    OnClickListener clickListener;
    private String guestPhotoUrl;
    boolean hideGuestProfilePhoto;
    CharSequence profilePlaceholderText;
    private AirDate requestEndDate;
    private AirDate requestStartDate;

    public void bind(HostCalendarReservationView view) {
        super.bind(view);
        view.bind(this.calendarDays, this.clickListener, this.requestStartDate, this.requestEndDate, this.guestPhotoUrl, this.hideGuestProfilePhoto, this.profilePlaceholderText);
    }

    public HostCalendarReservationEpoxyModel clickListener(OnClickListener clickListener2) {
        this.clickListener = clickListener2;
        return this;
    }

    public HostCalendarReservationEpoxyModel calendarDays(CalendarDays calendarDays2) {
        this.calendarDays = calendarDays2;
        return this;
    }

    public HostCalendarReservationEpoxyModel requestEndDate(AirDate requestEndDate2) {
        this.requestEndDate = requestEndDate2;
        return this;
    }

    public HostCalendarReservationEpoxyModel requestStartDate(AirDate requestStartDate2) {
        this.requestStartDate = requestStartDate2;
        return this;
    }

    public HostCalendarReservationEpoxyModel guestPhotoUrl(String guestPhotoUrl2) {
        this.guestPhotoUrl = guestPhotoUrl2;
        return this;
    }
}
