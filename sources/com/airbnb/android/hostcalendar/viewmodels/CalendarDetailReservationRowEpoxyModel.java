package com.airbnb.android.hostcalendar.viewmodels;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.CalendarDetailReservationRow;
import com.airbnb.android.hostcalendar.views.CalendarDetailReservationRow.CalendarDetailReservationClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public class CalendarDetailReservationRowEpoxyModel extends AirEpoxyModel<CalendarDetailReservationRow> {
    private boolean isNextDayBusy;
    private CalendarDetailReservationClickListener listener;
    private Reservation reservation;
    private boolean showTopSpace = true;

    public int getDefaultLayout() {
        return C6418R.layout.view_holder_calendar_detail_reservation_row;
    }

    public void bind(CalendarDetailReservationRow row) {
        super.bind(row);
        row.showTopSpace(this.showTopSpace);
        row.setReservation(this.reservation, this.isNextDayBusy);
        row.setReservationClickListener(this.listener);
    }

    public void unbind(CalendarDetailReservationRow row) {
        super.unbind(row);
        row.setOnClickListener(null);
    }

    public CalendarDetailReservationRowEpoxyModel showTopSpace(boolean show) {
        this.showTopSpace = show;
        return this;
    }

    public CalendarDetailReservationRowEpoxyModel reservation(Reservation reservation2) {
        this.reservation = reservation2;
        return this;
    }

    public CalendarDetailReservationRowEpoxyModel isNextDayBusy(boolean isNextDayBusy2) {
        this.isNextDayBusy = isNextDayBusy2;
        return this;
    }

    public CalendarDetailReservationRowEpoxyModel clickListener(CalendarDetailReservationClickListener listener2) {
        this.listener = listener2;
        return this;
    }

    public boolean overlaps(AirDate date) {
        return date.isBetweenInclusive(this.reservation.getCheckinDate(), this.reservation.getCheckoutDate().plusDays(-1));
    }
}
