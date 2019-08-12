package com.airbnb.android.hostcalendar.viewmodels;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.CalendarDetailMonthRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public class CalendarDetailMonthRowEpoxyModel extends AirEpoxyModel<CalendarDetailMonthRow> {
    private AirDate date;
    private boolean shortForm;

    public int getDefaultLayout() {
        return C6418R.layout.view_holder_calendar_detail_month_row;
    }

    public void bind(CalendarDetailMonthRow view) {
        super.bind(view);
        view.setMonthText(this.date, this.shortForm);
    }

    public CalendarDetailMonthRowEpoxyModel airDate(AirDate date2) {
        this.date = date2;
        return this;
    }

    public CalendarDetailMonthRowEpoxyModel shortForm(boolean shortForm2) {
        this.shortForm = shortForm2;
        return this;
    }
}
